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

@Component
@Profile("all")
public class DocumentConsumer {

    private final ObjectMapper objectMapper;
    private final String EXCHANGE_NAME = "user";
    private final ConverterService converterService;
    private final ChannelFactory channelFactory;
    private User user;

    public DocumentConsumer(ObjectMapper objectMapper, ConverterService converterService, ChannelFactory channelFactory) {
        this.objectMapper = objectMapper;
        this.converterService = converterService;
        this.channelFactory = channelFactory;
        consume(channelFactory.createChannel("user", "fanout"));
    }

    @SneakyThrows
    public void consume(Channel channel) {

        channel.basicQos(4);
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, EXCHANGE_NAME, "");

        DeliverCallback deliverCallback = (consumerTag, message) -> {

            this.user = User.from(objectMapper.readValue(message.getBody(), UserDto.class));
            converterService.convertAndSave(user);

            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            produce(channelFactory.createChannel("confirm", "direct"));
        };

        channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
        });

    }

    public void produce(Channel channel) {
        try {
            channel.basicPublish("confirm", "", null, objectMapper.writeValueAsBytes(user));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
