
package com.google.sps.data;

public class Comment {
  private String author;
  private String content;
  private long timestamp;
  public Comment(){}
  public Comment(String author, String content, long timestamp){
    this.author = author;
    this.content = content;
    this.timestamp = timestamp;
  }
}