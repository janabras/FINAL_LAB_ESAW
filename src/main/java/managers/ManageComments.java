package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Comment;
import utils.DB;

public class ManageComments {
private DB db = null ;
	
	public ManageComments() {
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
	public void addComment(Comment comment) {
		String query = "INSERT INTO comments (tid,username,content,postdatetime) VALUES (?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,comment.getTid());
			statement.setString(2,comment.getUsername());
			statement.setString(3,comment.getContent());
			statement.setTimestamp(4,comment.getPostDateTime());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Comment> getComments(){
		List<Comment> comments = new ArrayList<Comment>();
		String query = "SELECT id,username,content,tid, postdatetime, likes FROM comments ORDER BY postdatetime DESC;";
		PreparedStatement statement = null;
		try {
			 statement = db.prepareStatement(query);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Comment c = new Comment();
				 c.setId(rs.getInt("id"));
				 c.setUsername(rs.getString("username"));
				 c.setContent(rs.getString("content"));
				 c.setTid(rs.getInt("tid"));
				 c.setPostDateTime(rs.getTimestamp("postdatetime"));
				 c.setLikes(rs.getInt("likes"));
				 comments.add(c);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return comments;
	}
	
	public void deleteComment(int id) {
		String query = "DELETE FROM comments WHERE id = ?";
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
	
	/* check if likes comment */
	public Boolean isLikedComment(Integer id, Integer cid) {
		String query = "SELECT COUNT(*) FROM likescomment WHERE cid = ? AND id = ?;";
		PreparedStatement statement = null;
		Boolean returnstate = false;
		try {
			statement = db.prepareStatement(query);
            statement.setInt(1, cid);
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
	
	/*Modify User comment*/
	public void modifyOwner(String previous, String newUser) {
		String query = "UPDATE comments SET username = ? WHERE username = ?;";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1, newUser);
			statement.setString(2, previous);
			statement.executeUpdate();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/* Like/UndoLike a comment */
    public void addLikeComment(Integer id, Integer tid) {
        String query = "SELECT COUNT(*) AS count FROM likescomment WHERE cid = ? AND id = ?;"; //Check if the user likes the comment
        String query_likes = "SELECT likes FROM comments WHERE id = ?;"; //Gets number of likes
        String query_update = "UPDATE comments SET likes=? WHERE id = ?;"; //Updates number of likes
        String query_insert = "INSERT INTO likescomment (id, cid) VALUES (?,?)"; //Insert one like on table
        String query_delete = "DELETE FROM likescomment WHERE id=? AND cid = ?"; //Deletes like
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
	
}
