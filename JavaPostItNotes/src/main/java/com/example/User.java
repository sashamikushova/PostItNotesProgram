package com.example;


import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.apache.commons.codec.digest.DigestUtils;


public class User {
    @Id
    private ObjectId id;
    private String username;
    private String password;
    private String passwordHash;
    public User() {

    }

    public User(ObjectId id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = RandomStringUtils.random(32);
        this.passwordHash = DigestUtils.sha1Hex(password + this.password);

    }
    boolean comparePassword(String password){
        return DigestUtils.sha1Hex(password + this.password).equals(passwordHash);

    }
    public User( String username, String password) {
        this.username = username;
        this.password = RandomStringUtils.random(32);
        this.passwordHash = DigestUtils.sha1Hex(password + this.password);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
