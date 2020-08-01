package farmacia_legado;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import farmacia_legado.Models.Sell;
import javafx.collections.ObservableList;

public class GeneratePDF {

	public static boolean exportPDF(String date, ObservableList<Sell> listSells)
			throws DocumentException, MalformedURLException, IOException {
		String name = System.getProperty("user.home") + "/Desktop/" + date + ".pdf";
		Document pdf = new Document();
		PdfWriter.getInstance(pdf, new FileOutputStream(name));
		pdf.open();
		com.itextpdf.text.Image logo = com.itextpdf.text.Image
				.getInstance(System.getProperty("user.dir") + "/src/assets/images/legado_farmacia.png");
		logo.scaleToFit(100, 100);
		logo.setAlignment(Chunk.ALIGN_LEFT);
		logo.setAbsolutePosition(70, 720);
		Paragraph title = new Paragraph(date);
		title.setAlignment(Element.ALIGN_CENTER);
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph(" "));
		pdf.add(title);
		pdf.add(logo);
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph(" "));
		PdfPTable table = new PdfPTable(2);
		PdfPCell c1 = new PdfPCell(new Phrase("ID"));
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Total"));
		table.addCell(c1);
		table.setHeaderRows(1);
		for (Sell item : listSells) {
			table.addCell(String.valueOf(item.getId()));
			table.addCell(String.valueOf(item.getTotal()));
		}
		pdf.add(table);
		pdf.close();
		return true;
	}
}