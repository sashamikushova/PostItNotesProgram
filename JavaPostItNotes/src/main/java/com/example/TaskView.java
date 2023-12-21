package com.example;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.vaadin.crudui.crud.impl.GridCrud;


@Route("tasks")
@AnonymousAllowed
public class TaskView extends VerticalLayout {

    public TaskView(TaskService service) {
        RouterLink routerLink2 = new RouterLink("Notifications", PushView.class);
        add(routerLink2);
        GridCrud<Task> crud = new GridCrud<>(Task.class, service);
        crud.getGrid().setColumns("body", "date", "time");
        add(crud);
        setSizeFull();

    }


}

