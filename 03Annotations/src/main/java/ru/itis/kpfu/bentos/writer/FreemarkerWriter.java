package ru.itis.kpfu.bentos.writer;

import ru.itis.kpfu.bentos.annotations.*;

import javax.lang.model.element.Element;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

public class FreemarkerWriter {

    public void write(BufferedWriter writer, Element element, Set<? extends Element> annotatedFields) throws IOException {
        if (element.getAnnotation(MacroUse.class) == null) {
            Import importAnnotation = element.getAnnotation(Import.class);
            Macro macroAnnotation = element.getAnnotation(Macro.class);

            writer.write("<#import \"" + importAnnotation.path() + "\" as " + importAnnotation.name() + ">");
            writer.write("<html lang=\"en\"><body>");
            writer.write("<@" + importAnnotation.name() + "." + macroAnnotation.name() + ">" +
                    "</@" + importAnnotation.name() + "." + macroAnnotation.name() + ">");
            writer.write("</body></html>");
        } else {
            MacroUse useAnnotation = element.getAnnotation(MacroUse.class);
            HtmlForm form = element.getAnnotation(HtmlForm.class);

            writer.write("<#macro " + useAnnotation.macroName() + ">");
            writer.write("<form method=\"" + form.method() + "\" action=\"" + form.action() + "\">");

            for (Element field : annotatedFields) {
                var input = field.getAnnotation(HtmlInput.class);
                writer.write("<input type=\"" + input.type() + "\" name=\"" + input.name() + "\"" + " placeholder=\"" + input.placeholder() + "\">");
            }

            writer.write("</form>");
            writer.write("</#macro>");
        }

        writer.flush();
    }

}
