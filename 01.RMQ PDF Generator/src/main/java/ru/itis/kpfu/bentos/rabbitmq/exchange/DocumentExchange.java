package ru.itis.kpfu.bentos.rabbitmq.exchange;

import com.rabbitmq.client.Channel;
import ru.itis.kpfu.bentos.rabbitmq.ChannelFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DocumentExchange {

    private final ChannelFactory channelFactory;

    public DocumentExchange(ChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
    }

    public void publishTask(Channel channel, String user) {
        try {
            channel.basicPublish(channelFactory.getEXCHANGE_NAME(), "", null, user.getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
