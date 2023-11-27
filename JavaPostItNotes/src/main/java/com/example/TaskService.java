package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.SessionSynchronization;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Repository
public class TaskService implements CrudListener<Task> {

    private final TaskRepository repository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public TaskService(TaskRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Collection<Task> findAll() {
        // Get the user-specific database name from the instance of the configuration class
        String userDatabaseName = CustomMongoConfig.getDynamicDatabaseName();

        // Set the session synchronization to always use the correct database
        mongoTemplate.setSessionSynchronization(SessionSynchronization.ALWAYS);

        // Perform the find operation on the user-specific database
        return mongoTemplate.findAll(Task.class, userDatabaseName);
    }

    @Override
    public Task add(Task task) {
        // Get the user-specific database name
        String userDatabaseName = CustomMongoConfig.getDynamicDatabaseName();

        // Set the session synchronization to always use the correct database
        mongoTemplate.setSessionSynchronization(SessionSynchronization.ALWAYS);

        // Perform the insert operation on the user-specific database
        return mongoTemplate.insert(task, userDatabaseName);
    }

    @Override
    public Task update(Task task) {
        // Get the user-specific database name
        String userDatabaseName = CustomMongoConfig.getDynamicDatabaseName();

        // Set the session synchronization to always use the correct database
        mongoTemplate.setSessionSynchronization(SessionSynchronization.ALWAYS);

        // Perform the update operation on the user-specific database
        return mongoTemplate.save(task, userDatabaseName);
    }

    @Override
    public void delete(Task task) {
        // Get the user-specific database name
        String userDatabaseName = CustomMongoConfig.getDynamicDatabaseName();

        // Set the session synchronization to always use the correct database
        mongoTemplate.setSessionSynchronization(SessionSynchronization.ALWAYS);

        // Perform the delete operation on the user-specific database
        mongoTemplate.remove(task, userDatabaseName);
    }

}
