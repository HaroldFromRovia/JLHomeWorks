package ru.itis.kpfu.bentos.rmqdocumentmanager.controllers;

import com.beust.jcommander.JCommander;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itis.kpfu.bentos.rmqdocumentmanager.models.User;
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
    private final UserExchange userExchange;
    public final ObjectMapper objectMapper;

    @GetMapping("/start")
    public String start() {
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var channelFactory = new ChannelFactory();
        var exchange = new UserExchange();
        var channel = channelFactory.createChannel("user", "fanout");

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

    @PostMapping("/send")
    public ResponseEntity<HttpStatus> sendUser(@RequestBody UserDto user) throws JsonProcessingException {
        var channelFactory = new ChannelFactory();
        var channel = channelFactory.createChannel("user", "fanout");
        System.out.println(objectMapper.writeValueAsString(user));
        userExchange.publishTask(channel, objectMapper.writeValueAsString(user));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping
    public String get() {
        return "index";
    }

}
