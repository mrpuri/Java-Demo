package com.example.demo.test;

import javax.persistence.*;

@Entity
@Table(name="users", schema = "public")
public class Users {
@Id
private String username;

private String password;
private String usertype;
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
public String getUsertype() {
	return usertype;
}
public void setUsertype(String usertype) {
	this.usertype = usertype;
}

}
