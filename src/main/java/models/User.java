package models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import managers.ManageUsers;

public class User implements java.io.Serializable {
	
	ManageUsers manager = new ManageUsers();

	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private String name = "";
	private String mail = "";
	private String pwd = "";
	private Date dob;
	private String[] sport_interests;
	private String biography;
	private Boolean isAdmin = false;
	private String picture = "";
	
	public Date getDob() {
		return dob;
	}

	public static long getDateDiff(java.util.Date date1, java.util.Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
	
	public void setDob(Date dob) {
		java.util.Date currDate = new java.util.Date();    
		java.util.Date dobUtil = new java.util.Date(dob.getTime());
		this.dob = dob;
		
		if (getDateDiff(dobUtil, currDate, TimeUnit.DAYS) < 365 * 13) {
			error.put("age", true);
			//error[7] = true;
		} else {
			this.dob = dob;
			// error[7] = false;
			System.out.println("Date: " + dobUtil);
		}
	}


	public String[] getSport_interests() {
		return sport_interests;
	}


	public void setSport_interests(String[] sport_interests) {
		System.out.println("intereses: "+sport_interests[0]);
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
		 error.put("age", false);
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
		if (name.length() > 50) {
			// error[1] = true;
			error.put("user", true);
			this.name = "";
		}
		else if (!manager.isAvaiable(name, "name")) {
			// error[0] = true;
			// error[1] = false;
			error.put("user", true);
			this.name = name;
			System.out.println(name + " No disponible");
		} else {
			// error[0] = false;
			// error[1] = false;
			error.put("user", false);
			this.name = name;
			
			System.out.println("Username: " + name);
		}
	}
	public void setNameAndEmail(String name, String mail) {
		this.name = name;
		this.mail = mail;
	}
	
	public String getMail() {
		return this.mail;
	}
	
	public void setMail(String mail) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		if (matcher.matches()) {
			if (!manager.isAvaiable(mail, "mail")) {
				// error[4] = false;
				// error[3] = true;
				this.mail = mail;
				error.put("mail", true);
			} else {
				// error[4] = false;
				// error[3] = false;
				this.mail = mail;
				error.put("mail", false);
				System.out.println(mail);
			}
		} else {
			// error[4] = true;
			this.mail = "";
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
	
	public boolean getIsAdmin() {
        return isAdmin;
    }
	
	public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
		
}
