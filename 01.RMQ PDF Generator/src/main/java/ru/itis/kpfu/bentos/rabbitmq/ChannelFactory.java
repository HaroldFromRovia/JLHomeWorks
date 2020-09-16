package ru.itis.kpfu.bentos.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Data;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Data
public class ChannelFactory {

    private final String EXCHANGE_TYPE = "fanout";
    private final String EXCHANGE_NAME = "documents";
    private final String HOST = "localhost";

    public Channel createChannel() {

        var connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);

        Connection connection;
        Channel channel;

        try {

            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

            return channel;
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException("Could not establish connection with followed params", e);
        }

    }
}
