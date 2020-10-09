package ru.itis.kpfu.bentos.rmqdocumentmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.beans.BeanProperty;
import java.util.Objects;
import java.util.Properties;

@SpringBootApplication
public class RmqDocumentManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmqDocumentManagerApplication.class, args);
    }

}
