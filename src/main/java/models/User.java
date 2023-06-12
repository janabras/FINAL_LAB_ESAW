package models;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements java.io.Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private String name = "";
	private String mail = "";
	private String pwd = "";
	private Timestamp dob;
	private String sport_interests;
	private String biography;
	private Boolean isAdmin;
	private String picture = "";
	
	public Timestamp getDob() {
		return dob;
	}


	public void setDob(Timestamp dob) {
		this.dob = dob;
	}


	public String getSport_interests() {
		return sport_interests;
	}


	public void setSport_interests(String sport_interests) {
		this.sport_interests = sport_interests;
	}


	public String getBiography() {
		return biography;
	}


	public void setBiography(String biography) {
		this.biography = biography;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}

	

	private HashMap<String,Boolean> error = null;
	
	public User() {
		 error = new HashMap<String, Boolean>();
		 error.put("user", false);
		 error.put("mail", false);
	}
	
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMail() {
		return this.mail;
	}
	
	public void setMail(String mail) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		if (matcher.matches()) {
			this.mail = mail;
		} else {
			error.put("mail", true);
		}
		
	}
	
	public String getPwd() {
		return this.pwd;
	}
	
	public void setPwd(String pwd) {
		String regex = "^(?=.*[A-Z])(?=.*[!@#$&*-])(?=.{8,})\\S+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pwd);
		if (matcher.matches()) {
			this.pwd = pwd;
		} else {
			error.put("pwd", true);
		}
	}
	
	public HashMap<String,Boolean> getError() {
		return this.error;
	}
	
	public void setError(String name, boolean error) {
		this.error.put(name, error);
	}
	
	public boolean isAdmin() {
        return isAdmin;
    }
	
	public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
		
}
