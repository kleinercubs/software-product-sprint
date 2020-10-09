
package com.google.sps.data;

public class Comment {
  private String author;
  private String content;
  private String email;
  private boolean isAnonymous;
  private long timestamp;
  public Comment(){}
  public Comment(String author, String content, String email, boolean isAnonymous, long timestamp){
    this.author = author;
    this.content = content;
    this.email = email;
    this.isAnonymous = isAnonymous;
    this.timestamp = timestamp;
  }
}