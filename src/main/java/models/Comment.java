package models;

import java.sql.Timestamp;

public class Comment {
  private int id;
  private int tid;
  private String username;
  private String content;
  private Timestamp postDateTime;
  
  public Timestamp getPostDateTime() {
	return postDateTime;
}

public void setPostDateTime(Timestamp postDateTime) {
	this.postDateTime = postDateTime;
}

public Comment() {
  }
  
  public Comment(int tid, String username, String content) {
    this.tid = tid;
    this.username = username;
    this.content = content;
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getTid() {
    return tid;
  }
  
  public void setTid(int tid) {
    this.tid = tid;
  }
  
  public String getUsername() {
    return username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getContent() {
    return content;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
}
