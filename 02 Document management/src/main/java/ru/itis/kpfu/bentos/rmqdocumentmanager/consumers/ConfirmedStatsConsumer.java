package ru.itis.kpfu.bentos.rmqdocumentmanager.consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.rmqdocumentmanager.utils.ChannelFactory;

@Component
@Profile("all")
public class ConfirmedStatsConsumer {

    private final ChannelFactory channelFactory;

    private final String EXCHANGE_NAME = "document";
    private final String ROUTING_KEY = "confirm.confirmed.*";
    private int counter = 0;

    public ConfirmedStatsConsumer(ChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
        consume(channelFactory.createChannel(EXCHANGE_NAME, "topic"));
    }

    @SneakyThrows
    public void consume(Channel channel) {

        channel.basicQos(3);
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, EXCHANGE_NAME, ROUTING_KEY);

        DeliverCallback deliverCallback = (consumerTag, message) -> {

            counter++;

            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            System.out.println("Количество подвержденных документов = " + counter);
        };

        channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
        });
    }
}
