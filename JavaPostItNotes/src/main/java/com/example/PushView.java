package com.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@PageTitle("PostItNotes Notifications")
@Route(value = "push")
@AnonymousAllowed
public class PushView extends VerticalLayout {


    public PushView(WebPushService webPushService) {
        var toggle = new WebPushToggle(webPushService.getPublicKey());
        var messageInput = new TextField("Message:");
        var sendButton = new Button("Notify all users");
        var sendButton2 = new Button("Notify current user");
        var sendButton3 = new Button("Notify current user about tasks");
        var messageLayout = new HorizontalLayout(messageInput, sendButton, sendButton2, sendButton3);
        messageLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

        add(
                new H1("Manage PostItNotes Notifications"),
                toggle,
                messageLayout
        );

        toggle.addSubscribeListener(e -> {
            webPushService.subscribe(e.getSubscription());
        });
        toggle.addUnsubscribeListener(e -> {
            webPushService.unsubscribe(e.getSubscription());
        });


        sendButton.addClickListener(e -> webPushService.notifyAll("Message from user", messageInput.getValue()));
        sendButton2.addClickListener(e -> webPushService.notifyUser("Message to specific user "+ VaadinSession.getCurrent().getAttribute(User.class).getUsername(), messageInput.getValue()));
        sendButton3.addClickListener(e ->webPushService.checkAndSendNotifications());


    }
}