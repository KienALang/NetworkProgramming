package com.test.models.beans;


public class User {

  private long userId;
  private String username;
  private String password;
  private String fullName;
  private String email;
  private long roleId;

  public User(long userId, String username, String password, String fullName, String email, long roleId) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.email = email;
    this.roleId = roleId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }
}
