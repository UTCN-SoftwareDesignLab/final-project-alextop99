package com.assignments.project.receipt;

import com.assignments.project.receipt.dto.ReceiptDTO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.assignments.project.receipt.ReceiptType.PDF;

@Service
public class PdfReceiptService implements ReceiptService {

    @Override
    public String export(ReceiptDTO receiptDTO) {
        LocalDateTime now = LocalDateTime.now();
        String filename = dtf.format(now) + ".pdf";

        try (PDDocument receiptPDF = new PDDocument()) {
            PDPage page = new PDPage();
            receiptPDF.addPage(page);

            try (PDPageContentStream cont = new PDPageContentStream(receiptPDF, page)) {
                cont.beginText();
                cont.setFont(PDType1Font.TIMES_ROMAN, 12);
                cont.setLeading(14.5f);

                cont.newLineAtOffset(25, 700);

                cont.showText("PTI Service");
                cont.newLine();
                cont.newLine();

                cont.showText("Client Name: ");
                cont.showText(receiptDTO.getCar().getClient().getFirstname() + " " + receiptDTO.getCar().getClient().getLastname());
                cont.newLine();
                cont.showText("Car Details: ");
                cont.showText(receiptDTO.getCar().getMake() + " " + receiptDTO.getCar().getModel() + " " + receiptDTO.getCar().getYear());
                cont.showText(" " + receiptDTO.getCar().getMatNb() + " " + receiptDTO.getCar().getVin());
                cont.newLine();
                cont.newLine();

                cont.showText("Date: ");
                cont.showText(dtf.format(now));
                cont.newLine();

                cont.showText("Price: ");
                cont.showText(receiptDTO.getPrice().toString());
                cont.showText(" LEI");
                cont.newLine();

                cont.endText();
            }

            receiptPDF.save(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed";
        }

        return filename;
    }


    @Override
    public ReceiptType getType() {
        return PDF;
    }
}