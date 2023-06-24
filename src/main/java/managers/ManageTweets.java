package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import models.Tweet;
import utils.DB;


public class ManageTweets {
	
	private DB db = null ;
	 
	public ManageTweets() {
		try {
			db = new DB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalize() {
		try {
			db.disconnectBD();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/* Add a tweet */
	public void addTweet(Tweet tweet) {
		String query = "INSERT INTO tweets (uid,postdatetime,content) VALUES (?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,tweet.getUid());
			statement.setTimestamp(2,tweet.getPostDateTime());
			statement.setString(3,tweet.getContent());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/* Edit a tweet */
	public void editTweet(Tweet tweet) {
		String query = "UPDATE tweets SET content = ?, postdatetime = ? WHERE id = ?";
        try {
            PreparedStatement statement = db.prepareStatement(query);
            statement.setString(1, tweet.getContent());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setInt(3, tweet.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	/* check if likes tweet */
	public Boolean isLikedTweet(Integer id, Integer tid) {
		String query = "SELECT COUNT(*) FROM likes WHERE tid = ? AND id = ?;";
		PreparedStatement statement = null;
		Boolean returnstate = false;
		try {
			statement = db.prepareStatement(query);
            statement.setInt(1, tid);
            statement.setInt(2, id);
            ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int val = rs.getInt("COUNT(*)");
				if (val > 0) {
					returnstate = true;
				}
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnstate;
	}
	/* Like/UndoLike a tweet */
    public void addLikeTweet(Integer id, Integer tid) {
        String query = "SELECT COUNT(*) AS count FROM likes WHERE tid = ? AND id = ?;"; //Check if the user likes the tweet
        String query_likes = "SELECT likes FROM tweets WHERE id = ?;"; //Gets number of likes
        String query_update = "UPDATE tweets SET likes=? WHERE id = ?;"; //Updates number of likes
        String query_insert = "INSERT INTO likes (id, tid) VALUES (?,?)"; //Insert one like on table
        String query_delete = "DELETE FROM likes WHERE id=? AND tid = ?"; //Deletes like
        int n_likes = 0;
        PreparedStatement statement = null;
        PreparedStatement statement_likes = null;
        PreparedStatement statement_update = null;
        PreparedStatement statement_insert = null;
        PreparedStatement statement_delete = null;
        try {
            statement = db.prepareStatement(query);
            statement.setInt(1, tid);
            statement.setInt(2, id);
            ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int val = rs.getInt("count");
				statement_likes = db.prepareStatement(query_likes);
				statement_likes.setInt(1, tid);
				statement.close();
	            ResultSet rs_likes = statement_likes.executeQuery();
	            
	            if (rs_likes.next()) {
	            	n_likes = rs_likes.getInt("likes");
	            	
	            }
	            statement_likes.close();
				if (val > 0) { //user likes the tweet (delete likes)
					statement_delete = db.prepareStatement(query_delete);
					statement_delete.setInt(1, id);
					statement_delete.setInt(2, tid);
					n_likes = n_likes - 1;
					statement_delete.executeUpdate();
					statement_delete.close();
				} else {
					statement_insert = db.prepareStatement(query_insert);
					statement_insert.setInt(1, id);
					statement_insert.setInt(2, tid);
					n_likes = n_likes + 1;
		            statement_insert.executeUpdate();
		            statement_insert.close();
				}
				statement_update = db.prepareStatement(query_update);
				statement_update.setInt(1, n_likes);
				statement_update.setInt(2, tid);
				statement_update.executeUpdate();
				statement_update.close();
				
				
			}
        } catch (SQLException e) {
    			e.printStackTrace();
      }
    }
	
	/* Delete existing tweet */
	public void deleteTweet(Integer id) {
		String query = "DELETE FROM tweets WHERE id = ?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/* Get tweets from a user given start and end*/
	public List<Tweet> getUserTweets(Integer uid,Integer start, Integer end) {
		 String query = "SELECT * FROM tweets INNER JOIN users ON tweets.uid = users.id where tweets.uid = ? ORDER BY tweets.postdatetime DESC LIMIT ?,? ;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,uid);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
       		     tweet.setId(rs.getInt("id"));
				 tweet.setUid(rs.getInt("uid"));
				 tweet.setPostDateTime(rs.getTimestamp("postdatetime"));
				 tweet.setContent(rs.getString("content"));
				 tweet.setUname(rs.getString("name"));
				 tweet.setLikes(rs.getInt("likes"));
				 l.add(tweet);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	/* Get all tweets for Landing Page, most recent */
	public List<Tweet> getRecentTweets(Integer start, Integer end) {
		 String query = "SELECT tweets.id,tweets.uid,tweets.postdatetime,tweets.content, tweets.likes,users.name FROM tweets INNER JOIN users ON tweets.uid = users.id ORDER BY postdatetime DESC LIMIT ?,? ;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,start);
			 statement.setInt(2,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
      		     tweet.setId(rs.getInt("id"));
				 tweet.setUid(rs.getInt("uid"));
				 tweet.setPostDateTime(rs.getTimestamp("postdatetime"));
				 tweet.setContent(rs.getString("content"));
				 tweet.setUname(rs.getString("name"));
				 tweet.setLikes(rs.getInt("likes"));
				 l.add(tweet);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	/* Get all tweets for Landing Page, most recent */
	public List<Tweet> getLoggedTweets(Integer id, Integer start, Integer end) {
		String query = "SELECT t.id, t.uid, t.postdatetime, t.content, t.likes, u.name FROM tweets t LEFT JOIN users u ON (t.uid = u.id) WHERE t.uid != ? ORDER BY t.postdatetime DESC LIMIT ?, ?;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,id);
			 //statement.setInt(2,id);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
      		     tweet.setId(rs.getInt("id"));
				 tweet.setUid(rs.getInt("uid"));
				 tweet.setPostDateTime(rs.getTimestamp("postdatetime"));
				 tweet.setContent(rs.getString("content"));
				 tweet.setUname(rs.getString("name"));
				 tweet.setLikes(rs.getInt("likes"));
				 l.add(tweet);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return l;
	}
	
	/*Get tweets from users followed*/
	public List<Tweet> getUsersFollowedTweets(Integer id, Integer start, Integer end) {
		 String query = "SELECT t.id, t.uid, t.postdatetime, t.content, t.likes, u.name FROM tweets t LEFT JOIN follows f ON t.uid = f.fid  JOIN users u ON (t.uid = u.id) WHERE f.uid = ? ORDER BY t.postdatetime DESC LIMIT ?, ?;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,id);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
     		     tweet.setId(rs.getInt("id"));
				 tweet.setUid(rs.getInt("uid"));
				 tweet.setPostDateTime(rs.getTimestamp("postdatetime"));
				 tweet.setContent(rs.getString("content"));
				 tweet.setUname(rs.getString("name"));
				 tweet.setLikes(rs.getInt("likes"));
				 l.add(tweet);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
}