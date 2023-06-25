package managers;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import models.User;
import utils.DB;

public class ManageUsers {
	
	private DB db = null ;
	
	public ManageUsers() {
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
	
	/* Get a user given its PK*/
	public User getUser(Integer id) {
		String query = "SELECT id,name,mail,sport_interests FROM users WHERE id = ? ;";
		PreparedStatement statement = null;
		ResultSet rs = null;
		User user = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setNameAndEmail(rs.getString("name"), rs.getString("mail"));
				user.setSport_interests(rs.getString("sport_interests").substring(1, rs.getString("sport_interests").length() - 1).split("\\."));
				// user.setAdmin(rs.getBoolean("isAdmin"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	/* Get a user given its Username*/
	public User getUser(String uname) {
		String query = "SELECT id,name,mail,sport_interests FROM users WHERE name = ? ;";
		PreparedStatement statement = null;
		ResultSet rs = null;
		User user = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1, uname);
			rs = statement.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setNameAndEmail(rs.getString("name"), rs.getString("mail"));
				user.setSport_interests(rs.getString("sport_interests").substring(1, rs.getString("sport_interests").length() - 1).split("\\."));
				// user.setAdmin(rs.getBoolean("isAdmin"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/* Modify User */
	public void modifyUser(User u) {
		String query = "UPDATE users SET name = ?, mail = ?, pwd = ?, sport_interests = ? WHERE id = ?;";
		PreparedStatement statement = null;
		try {
			//Change owner of the comments
			String previousName = getUser(u.getId()).getName();
			ManageComments commentManager = new ManageComments();
			commentManager.modifyOwner(previousName, u.getName());
			
			statement = db.prepareStatement(query);
			statement.setString(1, u.getName());
			statement.setString(2, u.getMail());
			statement.setString(3, u.getPwd());
			statement.setString(4, Arrays.toString(u.getSport_interests()));
			// statement.setBoolean(3, u.isAdmin());
			statement.setInt(5, u.getId());
			statement.executeUpdate();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Check if user its admin
	public Boolean isAdmin(User u) {
		String query = "SELECT isAdmin FROM twitter_sports.users WHERE id=?;";
		Boolean returnVal = false;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1, u.getId());
			rs = statement.executeQuery();
			if (rs.next()) {
				returnVal = (rs.getInt("isAdmin") == 1);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnVal;
	}
	// Add new user
	public void addUser(User user) {
		String query = "INSERT INTO users (name, mail, pwd, dob, sport_interests, isAdmin) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1, user.getName());
			statement.setString(2, user.getMail());
			statement.setString(3, user.getPwd());
			statement.setDate(4,(java.sql.Date) user.getDob());
			statement.setString(5, Arrays.toString(user.getSport_interests()));
			statement.setInt(6, 0);
			// statement.setBoolean(4, user.isAdmin());
			statement.executeUpdate();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public boolean isAvaiable(String value, String field) {
		String query = "SELECT COUNT("+field+") AS count FROM users WHERE "+field+"=?";
		PreparedStatement statement = null;
		boolean returnStatement = false;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,value);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int val = rs.getInt("count");
				if (val == 0)
					returnStatement = true;
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnStatement;
	}
	
	
	public boolean isAvaiableWithoutId(String value, String field, int id) {
		String query = "SELECT COUNT("+field+") AS count FROM users WHERE "+field+"=? AND id != ?";
		PreparedStatement statement = null;
		boolean returnStatement = false;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,value);
			statement.setInt(2,id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int val = rs.getInt("count");
				if (val == 0)
					returnStatement = true;
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnStatement;
	}
	// Follow a user
	public void followUser(Integer uid, Integer fid) {
		String query = "INSERT INTO follows (uid,fid) VALUES (?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,uid);
			statement.setInt(2,fid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Unfollow a user
	public void unfollowUser(Integer uid, Integer fid) {
		String query = "DELETE FROM follows WHERE uid = ? AND fid = ?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,uid);
			statement.setInt(2,fid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// Get all the users
	public List<User> getUsers(Integer start, Integer end) {
		 String query = "SELECT id,name FROM users ORDER BY name ASC LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,start);
			 statement.setInt(2,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("id"));
				 user.setName(rs.getString("name"));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	public List<User> getNotFollowedUsers(Integer id, String[] interests, Integer start, Integer end) {
		// Modify this to suggest by same hobbies
		 String query = "SELECT id,name FROM users WHERE id NOT IN (SELECT id FROM users,follows WHERE id = fid AND uid = ?) AND id <> ? AND sport_interests = ? ORDER BY name LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,id);
			 statement.setInt(2, id);
			 statement.setString(3, Arrays.toString(interests));
			 statement.setInt(4,start);
			 statement.setInt(5,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("id"));
				 user.setName(rs.getString("name"));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	public List<User> getAllNotFollowedUsers(Integer id, Integer start, Integer end) {
		 String query = "SELECT id, name, sport_interests FROM users WHERE id NOT IN (SELECT id FROM users,follows WHERE id = fid AND uid = ?) AND id != ? ORDER BY name LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,id);
			 statement.setInt(2, id);
			 statement.setInt(3,start);
			 statement.setInt(4,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("id"));
				 user.setName(rs.getString("name"));
				 user.setSport_interests(rs.getString("sport_interests").substring(1, rs.getString("sport_interests").length() - 1).split("\\."));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	public List<User> getFollowedUsers(Integer id, Integer start, Integer end) {
		 String query = "SELECT id,name FROM users,follows WHERE id = fid AND uid = ? ORDER BY name LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,id);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("id"));
				 user.setName(rs.getString("name"));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	public List<User> getFollowersUsers(Integer id, Integer start, Integer end) {
		 String query = "SELECT id,name FROM users,follows WHERE id = uid AND fid = ? ORDER BY name LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,id);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("id"));
				 user.setName(rs.getString("name"));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	public Pair<Boolean,User> checkLogin(User user) {
		
		String query = "SELECT id,mail from users where name=? AND pwd=?";
		PreparedStatement statement = null;
		boolean output = false;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,user.getName());
			statement.setString(2,user.getPwd());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setMail(rs.getString("mail"));
				output = true;
			} 
			rs.close();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Pair.of(output,user);
		
	}
	
	public boolean checkUser(String user) {
		
		String query = "SELECT name from users where name=?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		boolean output = false;
		try {
			
			statement = db.prepareStatement(query);
			statement.setString(1,user);
			rs = statement.executeQuery();
			if (rs.isBeforeFirst()) {
				output = true;
			}
			rs.close();
			statement.close();
			return output;
			
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
		
	}
	
	public boolean chekMail(String mail) {
		
		String query = "SELECT mail from users where mail=?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		boolean output = false;
		try {
			
			statement = db.prepareStatement(query);
			statement.setString(1,mail);
			rs = statement.executeQuery();
			if (rs.isBeforeFirst()) {
				output = true;
			}
			rs.close();
			statement.close();
			return output;
			
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
		
	}
		
	/*Check if all the fields are filled correctly */
	public boolean isComplete(User user) {
		return(hasValue(user.getName()) &&
				hasValue(user.getMail()) &&
				hasValue(user.getDob().toString()) &&
				hasValue(user.getPwd()));
	}
	
	public boolean correctAge(User user) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -13);
		Date thirteenYearsAgo = calendar.getTime();
		return user.getDob().before(thirteenYearsAgo);
	}
	
	public boolean isLoginComplete(User user) {
	    return(hasValue(user.getName()) &&
	    	   hasValue(user.getPwd()) );
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
	 public void DeleteUserAccount(int id) {
	        String query = "DELETE FROM users WHERE id = ? ";
	        PreparedStatement statement = null;
	        try {
	            statement = db.prepareStatement(query);
	            statement.setInt(1, id);
	            statement.executeUpdate();
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		
	
	// TODO: add other methods 

}