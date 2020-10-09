package ru.itis.kpfu.bentos.rmqdocumentmanager.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.rmqdocumentmanager.models.User;
import ru.itis.kpfu.bentos.rmqdocumentmanager.models.UserDto;
import ru.itis.kpfu.bentos.rmqdocumentmanager.utils.ChannelFactory;

@Component
@Profile("all")
public class NotConfirmedConsumer {

    private final static String ROUTING_KEY = "confirm.not_confirmed";
    private final static String CONFIRMATION_EXCHANGE = "document";

    private final ObjectMapper objectMapper;
    private final ChannelFactory channelFactory;
    private User user;

    public NotConfirmedConsumer(ObjectMapper objectMapper,ChannelFactory channelFactory) {
        this.objectMapper = objectMapper;
        this.channelFactory = channelFactory;
        consume(channelFactory.createChannel(CONFIRMATION_EXCHANGE, "topic"));
    }

    @SneakyThrows
    public void consume(Channel channel) {

        channel.basicQos(4);
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, CONFIRMATION_EXCHANGE, ROUTING_KEY);

        DeliverCallback deliverCallback = (consumerTag, message) -> {

            this.user = User.from(objectMapper.readValue(message.getBody(), UserDto.class));

            System.out.println("Не подтвержден, никуда я ничего не буду отправлять");
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        };

        channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
        });
    }
}
