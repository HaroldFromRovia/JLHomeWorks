package ru.itis.kpfu.bentos.writer;

import ru.itis.kpfu.bentos.annotations.Import;
import ru.itis.kpfu.bentos.annotations.Macro;
import ru.itis.kpfu.bentos.annotations.MacroUse;

import javax.lang.model.element.Element;
import java.io.IOException;
import java.io.Writer;

public class FreemarkerWriter {

    public void write(Writer writer, Element element) throws IOException {

        Import importAnnotation = element.getAnnotation(Import.class);
        Macro macroAnnotation = element.getAnnotation(Macro.class);

        writer.write("<#import \"" + importAnnotation.path() + "\" as " + importAnnotation.name() + ">");
        writer.write("<html><body>");
        writer.write("<@" + importAnnotation.name() + "." + macroAnnotation.name() + ">" +
                "</@" + importAnnotation.name() + "." + macroAnnotation.name() + ">");
        writer.write("</body></html>");

        writer.close();
    }

}
