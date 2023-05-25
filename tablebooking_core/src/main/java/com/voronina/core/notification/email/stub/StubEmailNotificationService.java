package com.voronina.core.notification.email.stub;

import com.voronina.core.notification.email.EmailNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StubEmailNotificationService implements EmailNotificationService {

    @Override
    public void send(String from, String to, String message) {
        log.debug("Sending message: from={}, to={}, message{}", from, to, message);
        //TODO: реализовать отправку уведомлений через почту за неделю, день и час
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.debug("Sent message: from={}, to={}, message{}", from, to, message);
    }
}
