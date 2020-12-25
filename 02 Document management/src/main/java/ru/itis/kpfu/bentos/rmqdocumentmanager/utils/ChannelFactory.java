package ru.itis.kpfu.bentos.rmqdocumentmanager.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Data
@Component
@Profile({"confirm", "all"})
public class ChannelFactory {

    private final String HOST = "localhost";

    public Channel createChannel(String exchangeName, String exchangeType) {

        var connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);

        Connection connection;
        Channel channel;

        try {

            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(exchangeName, exchangeType);

            return channel;
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException("Could not establish connection with followed params", e);
        }
    }


}
