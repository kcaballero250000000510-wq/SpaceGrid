package com.techhub.spacegrid.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String role; // "LANDLORD" or "RENTER"

    public User(String username, String password, String fullName, String role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
    public String getRole() { return role; }
}