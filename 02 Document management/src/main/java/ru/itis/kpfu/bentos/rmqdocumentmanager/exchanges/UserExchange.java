package ru.itis.kpfu.bentos.rmqdocumentmanager.exchanges;

import com.rabbitmq.client.Channel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Profile("all")
public class UserExchange {

    public void publishTask(Channel channel, String user) {
        try {
            channel.basicPublish("user", "", null, user.getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
