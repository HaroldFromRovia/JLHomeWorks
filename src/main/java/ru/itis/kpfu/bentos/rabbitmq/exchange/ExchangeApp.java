package ru.itis.kpfu.bentos.rabbitmq.exchange;

import com.beust.jcommander.JCommander;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.kpfu.bentos.rabbitmq.ChannelFactory;
import ru.itis.kpfu.bentos.rabbitmq.models.UserDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExchangeApp {

    public final static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {

        //-commit --name Zagir --surname Din --passportId 1234 --age 20 --issueDate 28.07.2000

        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var channelFactory = new ChannelFactory();
        var exchange = new DocumentExchange(channelFactory);
        var channel = channelFactory.createChannel();

        while (true) {

            try {
                var input = bufferedReader.readLine();

                if (!input.isEmpty()) {

                    var command = input.split(" ")[0];
                    var user = UserDto.builder().build();
                    var argv = input.split(" ");

                    if (command.equals("-commit")) {

                        JCommander.newBuilder()
                                .addObject(user)
                                .build()
                                .parse(argv);


                        var jsonUser = objectMapper.writeValueAsString(user);
                        System.out.println(jsonUser);

                        exchange.publishTask(channel, jsonUser);

                    } else if (command.equals("exit")) {
                        System.out.println("Goodbye");
                        System.exit(0);
                    } else System.out.println("F**king slaves");
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
