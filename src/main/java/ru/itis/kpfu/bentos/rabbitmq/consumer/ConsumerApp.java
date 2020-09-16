package ru.itis.kpfu.bentos.rabbitmq.consumer;

import io.github.classgraph.ClassGraph;
import ru.itis.kpfu.bentos.rabbitmq.ChannelFactory;
import ru.itis.kpfu.bentos.rabbitmq.utils.DescendersUtil;

import java.util.stream.Collectors;

public class ConsumerApp {
    public static void main(String[] args) {

        var channelFactory = new ChannelFactory();
        var descenders = DescendersUtil.findDescenders("ru.itis.kpfu.bentos.rabbitmq",
                DocumentConsumer.class)
                .stream()
                .map( x -> (DocumentConsumer)x)
                .collect(Collectors.toList());

        for (DocumentConsumer descender : descenders) {

            var channel = channelFactory.createChannel();
            descender.consume(channel, channelFactory);

        }

    }
}
