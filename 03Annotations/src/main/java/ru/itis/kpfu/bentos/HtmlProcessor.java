package ru.itis.kpfu.bentos;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import ru.itis.kpfu.bentos.annotations.Html;
import ru.itis.kpfu.bentos.writer.FreemarkerWriter;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"ru.itis.kpfu.bentos.annotations.HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {

    private final FreemarkerWriter freemarkerWriter = new FreemarkerWriter();

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(Html.class);
        processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING, "Count of classes: " + annotatedElements.size());
        for (Element element : annotatedElements) {

            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(1) + element.getSimpleName().toString().toLowerCase() + ".ftl";
            Path out = Paths.get(path);

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));

                freemarkerWriter.write(writer, element);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return true;
    }
}