package ru.itis.kpfu.bentos.rmqdocumentmanager.consumers;

import com.rabbitmq.client.Channel;

public interface Consumer {
    void consume(Channel channel);
}
