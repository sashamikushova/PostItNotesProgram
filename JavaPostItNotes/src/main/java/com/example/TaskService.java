package com.example;

import org.springframework.stereotype.Repository;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;


@Repository
public class TaskService implements CrudListener<Task> {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public Task add(Task task) {
        return repository.insert(task);
    }

    @Override
    public Task update(Task task) {
        return repository.save(task);
    }

    @Override
    public void delete(Task task) {
        repository.delete(task);
    }




}

