import com.example.TaskService;
import com.example.UserService;
import com.example.WebPushService;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.jose4j.lang.JoseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.*;

class WebPushServiceTest {

    @Mock
    private TaskService taskService;

    @Mock
    private UserService userService;

    @InjectMocks
    private WebPushService webPushService;

    @BeforeEach
    void setUp() throws GeneralSecurityException {
        MockitoAnnotations.initMocks(this);
        webPushService = new WebPushService(taskService, userService);
    }

    @Test
    void testSendNotification() throws JoseException, GeneralSecurityException, IOException, ExecutionException, InterruptedException {
        Subscription mockSubscription = mock(Subscription.class);
        PushService mockPushService = mock(PushService.class);
        Subscription.Keys mockKeys = mock(Subscription.Keys.class);
        when(mockSubscription.keys).thenReturn(mockKeys);
        webPushService.sendNotification(mockSubscription, "Test message");
        verify(mockPushService, times(1)).send(any(Notification.class));
    }
}
