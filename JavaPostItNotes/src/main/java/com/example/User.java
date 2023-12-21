package com.example;


import nl.martijndwars.webpush.Subscription;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;


public class User {
    @Id
    private ObjectId id;
    private String username;
    private String password;
    private String passwordHash;
    private Map<String, Subscription> endpointToSubscription = new HashMap<>();

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

    public Map<String, Subscription> getEndpointToSubscription() {
        return endpointToSubscription;
    }

    public void setEndpointToSubscription(HashMap<String, Subscription> endpointToSubscription) {
        this.endpointToSubscription = endpointToSubscription;
    }
    public void addSubscriptionToMap(Subscription subscription){
        this.endpointToSubscription.put(subscription.endpoint, subscription);
    }
    public void removeSubscriptionFromMap(Subscription subscription){
        this.endpointToSubscription.remove(subscription.endpoint);
    }
}
