package ru.itis.kpfu.bentos.rmqdocumentmanager.services.implementations;

import com.itextpdf.html2pdf.HtmlConverter;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.bentos.rmqdocumentmanager.models.User;
import ru.itis.kpfu.bentos.rmqdocumentmanager.services.interfaces.ConverterService;
import ru.itis.kpfu.bentos.rmqdocumentmanager.services.interfaces.FreemarkerTemplateService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
@AllArgsConstructor
@Profile("all")
public class ConverterServiceImpl implements ConverterService {

    private final FreemarkerTemplateService freemarkerTemplateService;

    @SneakyThrows
    @Override
    public void convertAndSave(User user) {
        var html = freemarkerTemplateService.fillTemplate(user);
        var file = new File("result/doc.pdf");
        file.getParentFile().mkdirs();
        var outputStream = new FileOutputStream(file);
        HtmlConverter.convertToPdf(html, outputStream);
    }
}
