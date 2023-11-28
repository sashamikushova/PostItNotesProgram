package com.example;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, Integer> {

    @Query("{username:'?0'}")
    User findUserByUsername(String username);



}
