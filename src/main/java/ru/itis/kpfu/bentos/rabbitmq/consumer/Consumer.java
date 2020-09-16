package ru.itis.kpfu.bentos.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.itis.kpfu.bentos.rabbitmq.ChannelFactory;

public interface Consumer {

    void consume(Channel channel, ChannelFactory factory);

}
