package client.viewResolver;

import client.model.Ticket;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.awt.*;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document, PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        Ticket ticket = (Ticket) model.get("ticket");

        PdfPTable table = new PdfPTable(3);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);

        table.addCell("Name");
        table.addCell("Time");
        table.addCell("User");

        table.addCell(ticket.getEvent().getName());
        table.addCell(ticket.getDateTime().toString());
        table.addCell(ticket.getUser().getName());

        document.add(table);

    }


}