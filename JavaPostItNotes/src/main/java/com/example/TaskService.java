package com.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.vaadin.crudui.crud.CrudListener;
import java.util.List;


@Repository
public class TaskService implements CrudListener<Task> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TaskService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        }


    @Override
    public List<Task> findAll() {
        return mongoTemplate.findAll(Task.class, CustomMongoConfig.dynamicCollectionName);
    }

    @Override
    public Task add(Task task) {
        mongoTemplate.save(task, CustomMongoConfig.dynamicCollectionName);
        return task;
    }

    @Override
    public Task update(Task task) {
        mongoTemplate.save(task, CustomMongoConfig.dynamicCollectionName);
        return task;
    }

    @Override
    public void delete(Task task) {
        mongoTemplate.remove(task, CustomMongoConfig.dynamicCollectionName);
    }
}
