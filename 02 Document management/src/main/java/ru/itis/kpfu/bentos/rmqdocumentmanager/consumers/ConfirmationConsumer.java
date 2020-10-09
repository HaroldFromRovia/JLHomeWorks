package ru.itis.kpfu.bentos.rmqdocumentmanager.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.rmqdocumentmanager.models.User;
import ru.itis.kpfu.bentos.rmqdocumentmanager.models.UserDto;
import ru.itis.kpfu.bentos.rmqdocumentmanager.services.interfaces.ConverterService;
import ru.itis.kpfu.bentos.rmqdocumentmanager.utils.ChannelFactory;

import java.io.IOException;
import java.util.Scanner;

@Component
@Profile("confirm")
public class ConfirmationConsumer {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final String EXCHANGE_NAME = "confirm";
    private final ChannelFactory channelFactory;
    private final static String CONFIRMED_ROUTING_KEY = "confirm.confirmed.email";
    private final static String NOT_CONFIRMED_ROUTING_KEY = "confirm.not_confirmed";
    private User user;

    public ConfirmationConsumer(ChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
        consume(channelFactory.createChannel("confirm", "direct"));
    }

    @SneakyThrows
    public void consume(Channel channel) {

        channel.basicQos(4);
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, EXCHANGE_NAME, "");

        var sc = new Scanner(System.in);

        DeliverCallback deliverCallback = (consumerTag, message) -> {

            String key;
            this.user = User.from(objectMapper.readValue(message.getBody(), UserDto.class));
            System.out.println(user.toString());
            if (sc.nextLine().equals("confirm")) {
                System.out.println("Подтверждено");
                key = CONFIRMED_ROUTING_KEY;
            } else {
                System.out.println("Не подтверждено");
                key = NOT_CONFIRMED_ROUTING_KEY;
            }
            produce(channelFactory.createChannel("document", "topic"), key);

            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        };

        channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
        });
    }

    public void produce(Channel channel, String routingKey) {
        try {
            channel.basicPublish("document", routingKey, null, objectMapper.writeValueAsBytes(user));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
