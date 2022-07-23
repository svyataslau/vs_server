package com.example.vs_server.model;

public class Promise {

  private long id;
  private String title;

  public Promise() {

  }
  
  public Promise(long id, String title) {
    this.id = id;
    this.title = title;
  }

  public Promise(String title) {
    this.title = title;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Promise [id=" + id + ", title=" + title  + "]";
  }

}
