package ru.itis.kpfu.bentos.rmqdocumentmanager.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.kpfu.bentos.rmqdocumentmanager.models.User;
import ru.itis.kpfu.bentos.rmqdocumentmanager.services.interfaces.FreemarkerTemplateService;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Profile("all")
public class FreemarkerTemplateServiceImpl implements FreemarkerTemplateService {

    private final FreeMarkerConfigurer configurer;

    @SneakyThrows
    @Override
    public String fillTemplate(User user) {

        var writer = new StringWriter();
        Map<String, User> map = new HashMap<>();
        map.put("user", user);
        configurer.getConfiguration().getTemplate("main.ftl").process(map, writer);
        System.out.println();
        return writer.toString();
    }
}
