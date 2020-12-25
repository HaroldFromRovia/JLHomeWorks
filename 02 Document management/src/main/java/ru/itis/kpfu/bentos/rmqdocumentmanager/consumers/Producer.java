package ru.itis.kpfu.bentos.rmqdocumentmanager.consumers;

import com.rabbitmq.client.Channel;

public interface Producer {
    void produce(Channel channel);

    void produce(Channel channel, String key);
}
