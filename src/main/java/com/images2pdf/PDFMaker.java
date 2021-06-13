package com.images2pdf;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Alert;

import javax.imageio.ImageIO;

public class PDFMaker {

    public void makePDF(List<ImageType> imageFiles, File location, String fileName) throws IOException, DocumentException {
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream((new File(location, fileName))));
        document.open();

        for (ImageType image : imageFiles) {
            document.setPageSize(PageSize.A4.rotate());
            document.newPage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image.getImage(), "jpg", baos);
            com.itextpdf.text.Image nimage = com.itextpdf.text.Image.getInstance(baos.toByteArray());
            nimage.scaleToFit(PageSize.A4.rotate());
            float x = (PageSize.A4.rotate().getWidth() - nimage.getScaledWidth()) / 2;
            float y = (PageSize.A4.rotate().getHeight() - nimage.getScaledHeight()) / 2;
            nimage.setAbsolutePosition(x, y);
            nimage.setBorderWidth(0);

            document.add(nimage);
        }
        document.close();
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Success");
        success.setHeaderText("PDF was created");
        success.show();
    }

}
