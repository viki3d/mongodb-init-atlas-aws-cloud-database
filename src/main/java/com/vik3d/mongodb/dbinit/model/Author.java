package com.vik3d.mongodb.dbinit.model;

/**
 * The entity representing book author.
 * No more Lombok. Now considered as a bad practice.
 *
 * @author viki3d
 */
public class Author {

  private String firstname;
  private String lastname;
  private String country;

  public String getFirstname() {
    return firstname;
  }
  
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }
  
  public String getLastname() {
    return lastname;
  }
  
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getCountry() {
    return country;
  }
  
  public void setCountry(String country) {
    this.country = country;
  }

}
