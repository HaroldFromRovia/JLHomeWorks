package ru.itis.kpfu.bentos.rabbitmq.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.layout.property.TextAlignment;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import lombok.SneakyThrows;
import ru.itis.kpfu.bentos.rabbitmq.ChannelFactory;
import ru.itis.kpfu.bentos.rabbitmq.models.User;
import ru.itis.kpfu.bentos.rabbitmq.models.UserDto;
import ru.itis.kpfu.bentos.rabbitmq.utils.DocumentPDFWriter;

public class ExpulsionConsumer implements DocumentConsumer {

    private ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void consume(Channel channel, ChannelFactory factory) {

        channel.basicQos(3);
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, factory.getEXCHANGE_NAME(), "");

        DeliverCallback deliverCallback = (consumerTag, message) -> {

            DocumentPDFWriter pdfWriter = new DocumentPDFWriter();
            var user = User.from(objectMapper.readValue(message.getBody(), UserDto.class));

            pdfWriter.createTemplate(pdfWriter.createFile("expulsion.pdf"))
                    .fillHeader("Ректору КФУ\n" +
                            "Гафурову И.Р.\n" +
                            "от ", user)
                    .fillBody("Заявление\n", TextAlignment.CENTER)
                    .fillBody("    Турум пум пум.", TextAlignment.LEFT)
                    .fillFooter("Дата")
                    .fillFooter("Подпись")
                    .close();
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        };

        channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
        });

    }

}
