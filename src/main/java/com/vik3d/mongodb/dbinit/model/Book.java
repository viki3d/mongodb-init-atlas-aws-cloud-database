package com.vik3d.mongodb.dbinit.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The entity representing book.
 * No more Lombok. Now considered as a bad practice.
 *
 * @author viki3d
 */
public class Book {

  private int id;
  private String title;
  private int year;
  private String[] tags;
  private Author author;

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String[] getTags() {
    return tags;
  }
  
  public void setTags(String[] tags) {
    this.tags = tags;
  }

  /** Convert book entity to JSON expression.  */
  public String toJsonString() {
    String jsonString = null;
    try {
      jsonString = new ObjectMapper().writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return jsonString;
  }

}
