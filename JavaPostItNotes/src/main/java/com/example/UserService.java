package com.example;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.vaadin.crudui.crud.CrudListener;

import java.util.List;


@Repository
public class UserService implements CrudListener<User> {

    private final  UserRepository repository;
    private final MongoTemplate mongoTemplate;

    public UserService(UserRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class, CustomMongoConfig.dynamicCollectionName);}

    @Override
    public User add(User user) {
        return repository.insert(user);
    }


    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }
    public User findUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }
}
