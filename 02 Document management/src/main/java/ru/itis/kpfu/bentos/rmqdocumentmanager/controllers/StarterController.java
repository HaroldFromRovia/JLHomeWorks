package ru.itis.kpfu.bentos.rmqdocumentmanager.controllers;

import com.beust.jcommander.JCommander;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.kpfu.bentos.rmqdocumentmanager.utils.ChannelFactory;
import ru.itis.kpfu.bentos.rmqdocumentmanager.exchanges.UserExchange;
import ru.itis.kpfu.bentos.rmqdocumentmanager.models.UserDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
@AllArgsConstructor
@Profile("all")
public class StarterController {

    //-commit --name Zagir --surname Din --passportId 1234 --age 20 --issueDate 28.07.2000 --phone 89123456712

    public final  ObjectMapper objectMapper;

    @GetMapping
    public void start(){
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var channelFactory = new ChannelFactory();
        var exchange = new UserExchange();
        var channel = channelFactory.createChannel("user","fanout");

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
                        exchange.publishTask(channel, jsonUser);

                    } else if (command.equals("exit")) {
                        System.out.println("Goodbye");
                        System.exit(0);
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
