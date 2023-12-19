package com.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Web Push")
@Route(value = "push")
@AnonymousAllowed
public class PushView extends VerticalLayout {

    public PushView(WebPushService webPushService) {

        var toggle = new WebPushToggle(webPushService.getPublicKey());
        var messageInput = new TextField("Message:");
        var sendButton = new Button("Notify all users!");
        var messageLayout = new HorizontalLayout(messageInput, sendButton);
        messageLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

        add(
                new H1("Web Push Notification Demo"),
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
    }
}