package vku.pntq.inventorymanagement.DAO;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PdfDao {

    public void luuThanhPDF(WritableImage writableImage, String duongDan) throws IOException {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);

        File tempImageFile = File.createTempFile("tempImage", ".png");
        ImageIO.write(bufferedImage, "png", tempImageFile);

        PdfWriter writer = new PdfWriter(duongDan);
        PdfDocument pdfDocument = new PdfDocument(writer);

        Document document = new Document(pdfDocument);

        Image image = new Image(ImageDataFactory.create(tempImageFile.getAbsolutePath()));
        document.add(image);


        document.close();

        tempImageFile.delete();
    }
}
