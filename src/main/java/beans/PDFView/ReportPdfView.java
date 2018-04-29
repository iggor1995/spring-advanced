package beans.PDFView;

import beans.models.Ticket;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.text.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import com.itextpdf.text.pdf.PdfPTable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("reportView")
public class ReportPdfView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=myReport.pdf");
        List<Ticket> tickets = (List<Ticket>) model.get("report");

        //IText API
        Document pdfDocument = new Document();
        PdfWriter.getInstance(pdfDocument, response.getOutputStream());

        //title
        Paragraph title = new Paragraph("Tickets");
        title.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA));
        title.setFontSize(18f);
        title.setItalic();
        // a table with three columns
        PdfPTable table = new PdfPTable(5);
        // the cell object
        table.addCell("Ticket Id");
        table.addCell("User name");
        table.addCell("Event name");
        table.addCell("Price");
        table.addCell("Date");
        if(tickets.size() > 0){
            for(Ticket ticket : tickets){
                table.addCell(String.valueOf(ticket.getId()));
                table.addCell(String.valueOf(ticket.getUser().getName()));
                table.addCell(String.valueOf(ticket.getEvent().getName()));
                table.addCell(String.valueOf(ticket.getPrice()));
                table.addCell(String.valueOf(ticket.getDateTime()));
            }
        }
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        //content
        pdfDocument.open();
        pdfDocument.add(table);

        pdfDocument.close();
    }
}