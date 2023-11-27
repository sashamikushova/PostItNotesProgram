package com.example;

import org.springframework.stereotype.Repository;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;


@Repository
public class UserService implements CrudListener<User> {

    private final  UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<User> findAll() {
        return null;
    }

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
