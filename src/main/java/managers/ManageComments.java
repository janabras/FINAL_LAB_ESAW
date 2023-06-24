package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		String query = "SELECT id,username,content,tid, postdatetime FROM comments ORDER BY postdatetime DESC;";
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
}
