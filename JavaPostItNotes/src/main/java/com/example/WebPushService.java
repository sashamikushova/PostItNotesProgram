package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.PostConstruct;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class WebPushService {

    @Value("${VAPID_PUBLIC_KEY}")
    private String publicKey;
    @Value("${VAPID_PRIVATE_KEY}")
    private String privateKey;
    @Value("${VAPID_SUBJECT}")
    private String subject;

    //private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private User currentUser;
    public PushService pushService;
    private TaskService taskService;
    private final UserService userService;

    private final Map<String, Subscription> endpointToSubscription = new HashMap<>();

    @PostConstruct
    private void init() throws GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        pushService = new PushService(publicKey, privateKey, subject);
    }
    @Autowired
    public WebPushService(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
        Security.addProvider(new BouncyCastleProvider());
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void sendNotification(Subscription subscription, String messageJson) {
        try {
            HttpResponse response = pushService.send(new Notification(subscription, messageJson));
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 201) {
                System.out.println("Server error, status code:" + statusCode);
                InputStream content = response.getEntity().getContent();
                List<String> strings = IOUtils.readLines(content, "UTF-8");
                System.out.println(strings);
            }
        } catch (GeneralSecurityException | IOException | JoseException | ExecutionException
                 | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(Subscription subscription) {
        System.out.println("Subscribed to " + subscription.endpoint);
        User currentUser = VaadinSession.getCurrent().getAttribute(User.class);
        this.currentUser = currentUser;
        endpointToSubscription.put(subscription.endpoint, subscription);
        currentUser.addSubscriptionToMap(subscription);

    }

    public void unsubscribe(Subscription subscription) {
        User currentUser = VaadinSession.getCurrent().getAttribute(User.class);
        endpointToSubscription.remove(subscription.endpoint);
        currentUser.removeSubscriptionFromMap(subscription);
        System.out.println("Unsubscribed " + subscription.endpoint + " auth:" + subscription.keys.auth);
    }


    public record Message(String title, String body) {
    }

    ObjectMapper mapper = new ObjectMapper();

    public void notifyAll(String title, String body) {
        try {
            String msg = mapper.writeValueAsString(new Message(title, body));
            endpointToSubscription.values().forEach(subscription -> {
                sendNotification(subscription, msg);
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void notifyUser(String title, String body) {
        try {
            User currentUser = this.currentUser;
            String msg = mapper.writeValueAsString(new Message(title, body));
            currentUser.getEndpointToSubscription().values().forEach(subscription -> {
                sendNotification(subscription, msg);
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public void notifyUserAboutTask(String username, Task task) {
        try {
            String msg = mapper.writeValueAsString(new Message("PostItNotes Task today at " + task.getTime(), task.getBody()));
            notifyUser("PostItNotes Task today at " + task.getTime(), task.getBody());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(fixedDelay = 120000) //2 minutes
    public void checkAndSendNotifications() {
        System.out.println("Checking and sending notifications...");
        try {
            List<Task> tasks = this.taskService.findAll();
            for (Task task : tasks) {
                LocalDate currentDate = LocalDate.now();
                if (task.getDate().isEqual(currentDate)) {
                    LocalTime currentTime = LocalTime.now();
                    System.out.println("Current time: " + currentTime);
                    LocalTime taskTime = LocalTime.parse(task.getTime());
                    System.out.println("Task time: " + taskTime);
                    if (ChronoUnit.MINUTES.between(currentTime, taskTime) <= 2) {
                        System.out.println("Sending notification for task: " + task.getId());
                        String username = CustomMongoConfig.getDynamicCollectionName();
                        System.out.println(username);
                        notifyUserAboutTask(username,task);
                        System.out.println("Notification sent.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    public PushService getPushService() {
        return pushService;
    }
}