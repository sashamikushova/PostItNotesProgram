package com.example;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.vaadin.crudui.crud.impl.GridCrud;


@Route("tasks")
@AnonymousAllowed
public class TaskView extends VerticalLayout {

    public TaskView(TaskService service) {
        GridCrud<Task> crud = new GridCrud<>(Task.class, service);
        add(crud);
        setSizeFull();
    }


}

