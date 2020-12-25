package ru.itis.kpfu.bentos.rabbitmq.utils;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import lombok.SneakyThrows;
import ru.itis.kpfu.bentos.rabbitmq.models.User;

import java.io.File;
import java.io.IOException;

public class DocumentPDFWriter {

    //Medieval.ttf
    //pt-sans.ttf
    //MedievalInitialOne.ttf

    private final String DESTINATION = "results/";
    private final String FONT_FOLDER = "src/main/resources/fonts/pt-sans.ttf";
    private final PdfFont FONT = PdfFontFactory.createFont(FONT_FOLDER, "Cp1251", true);
    private Document document;

    public DocumentPDFWriter() throws IOException {
    }

    public String createFile(String filename) {
        var destination = DESTINATION + filename;
        var file = new File(DESTINATION + filename);

        file.getParentFile().mkdirs();

        return destination;
    }

    @SneakyThrows
    public DocumentPDFWriter createTemplate(String destination) {

        var writer = new PdfWriter(destination);
        var pdf = new PdfDocument(writer);
        document = new Document(pdf, PageSize.A4);
        document.setMargins(20, 20, 20, 20);

        return this;
    }

    public DocumentPDFWriter fillHeader(String header, User user) {

        var text = header + user.getName() + " " + user.getSurname() + "\n" + "Номер паспорта: "
                + user.getPassportId() + "\n" + "Дата выдачи: " + user.getDateOfIssue() + "\n\n\n\n\n";

        var paragraph = new Paragraph(text);
        paragraph.setTextAlignment(TextAlignment.RIGHT);
        paragraph.setFont(FONT);

        document.add(paragraph);

        return this;
    }

    public DocumentPDFWriter fillBody(String body, TextAlignment alignment) {

        var paragraph = new Paragraph(body);
        paragraph.setTextAlignment(alignment);
        paragraph.setFont(FONT);

        document.add(paragraph);

        return this;
    }

    public DocumentPDFWriter fillFooter(String footer) {

        var paragraph = new Paragraph(footer + "_________________");
        paragraph.setTextAlignment(TextAlignment.RIGHT);
        paragraph.setFont(FONT);

        document.add(paragraph);

        return this;
    }

    public void close() {
        document.close();
    }

    private Cell getCell(String text, TextAlignment alignment) {
        var cell = new Cell().add(new Paragraph(text));

        cell.setPadding(0);
        cell.setTextAlignment(alignment);
        cell.setBorder(Border.NO_BORDER);

        return cell;
    }
}
