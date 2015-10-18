package hemoterapia.print;

import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import hemoterapia.domain.Person;

public class Printer {

	public Printer() {

	}

	private float pt2mm(float pt) {
		return pt * 25.4f / 72;
	}

	private PDDocument createDoc() {
		return new PDDocument();
	}

	private PDPage setPage(PDRectangle size) {
		return new PDPage(size);
	}

	private void drawHeaderPDF(PDDocument doc, PDPageContentStream contentStream, PDRectangle mediabox)
			throws IOException {
		BufferedImage tmp_image = ImageIO.read(new File("./src/main/webapp/img/logo.png"));
		BufferedImage image = new BufferedImage(tmp_image.getWidth(), tmp_image.getHeight(),
				BufferedImage.TYPE_4BYTE_ABGR);
		image.createGraphics().drawRenderedImage(tmp_image, null);
		PDXObjectImage logoImage = new PDPixelMap(doc, image);

		float margin = 50;

		float headerX = mediabox.getLowerLeftX() + margin;
		float headerY = mediabox.getUpperRightY() - margin - 10;
		float headerWidth = mediabox.getWidth() - 2 * margin;
		float headerHeight = 40;

		System.out.println("Print medidas: " + mediabox.getLowerLeftX() + " - " + mediabox.getUpperRightY() + " - "
				+ mediabox.getWidth() + " - " + mediabox.getHeight());
		System.out.println("Print medidas: " + headerX + " - " + headerY + " - " + headerWidth + " - " + headerHeight);

		contentStream.drawXObject(logoImage, headerX, headerY, headerWidth, headerHeight);
		// contentStream.drawXObject(logoImage, 50, 780, 500, 40);
	}

	private String createTextTicket(Person person) {
		String completeName = person.getName() + " " + person.getSurname();
		Double total2Pay = person.getAmountToPaid();

		return "Recibe de " + completeName + " la suma de pesos " + total2Pay.toString()
				+ " en concepto de inscripción al 29° encuentro provincial"
				+ " de hemoterapia a realizarse los días 4, 5 y 6 de noviembre de 2015 en la ciudad de Villa Gesel, provincia"
				+ " de Buenos Aires, Argentina";

	}

	private List<String> getTextInLines(String text, PDFont pdfFont, float fontSize, float width) throws IOException {

		List<String> lines = new ArrayList<String>();
		int lastSpace = -1;
		while (text.length() > 0) {
			int spaceIndex = text.indexOf(' ', lastSpace + 1);
			if (spaceIndex < 0)
				spaceIndex = text.length();
			String subString = text.substring(0, spaceIndex);
			float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
			System.out.printf("'%s' - %f of %f\n", subString, size, width);
			if (size > width) {
				if (lastSpace < 0)
					lastSpace = spaceIndex;
				subString = text.substring(0, lastSpace);
				lines.add(subString);
				text = text.substring(lastSpace).trim();
				System.out.printf("'%s' is line\n", subString);
				lastSpace = -1;
			} else if (spaceIndex == text.length()) {
				lines.add(text);
				System.out.printf("'%s' is line\n", text);
				text = "";
			} else {
				lastSpace = spaceIndex;
			}
		}
		return lines;
	}

	public void printRegistrationTicket(Person person) {
		try {
			PDDocument doc = createDoc();
			PDPage page = setPage(PDPage.PAGE_SIZE_A4);

			doc.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(doc, page);

			PDFont pdfFont = PDType1Font.HELVETICA;
			float fontSize = 10;
			float leading = 1.5f * fontSize;

			PDRectangle mediabox = page.findMediaBox();
			float margin = 60;
			float width = mediabox.getWidth() - 2 * margin;
			float startX = mediabox.getLowerLeftX() + margin;
			float startY = mediabox.getUpperRightY() - margin - 45;

			String text = createTextTicket(person);
			List<String> lines = getTextInLines(text, pdfFont, fontSize, width);

			drawHeaderPDF(doc, contentStream, mediabox);

			contentStream.beginText();
			contentStream.setFont(pdfFont, fontSize);
			contentStream.moveTextPositionByAmount(startX, startY);
			for (String line : lines) {
				contentStream.drawString(line);
				contentStream.moveTextPositionByAmount(0, -leading);
			}
			contentStream.endText();
			contentStream.close();

			doc.save("test.pdf");
		} catch (IOException | COSVisitorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printRegistrationTicket2(Person person) {
		System.out.println("VOY a IMPRIMIR......");
		// Create a document and add a page to it
		PDDocument document = new PDDocument();
		PDPage page = new PDPage(PDPage.PAGE_SIZE_A4);

		document.addPage(page);

		// Create a new font object selecting one of the PDF base fonts
		PDFont font = PDType1Font.HELVETICA_BOLD;

		// Start a new content stream which will "hold" the to be created
		// content
		PDPageContentStream contentStream;
		try {
			contentStream = new PDPageContentStream(document, page);

			BufferedImage tmp_image = ImageIO.read(new File("./src/main/webapp/img/logo.png"));
			BufferedImage image = new BufferedImage(tmp_image.getWidth(), tmp_image.getHeight(),
					BufferedImage.TYPE_4BYTE_ABGR);
			image.createGraphics().drawRenderedImage(tmp_image, null);
			PDXObjectImage logoImage = new PDPixelMap(document, image);

			contentStream.drawXObject(logoImage, 35, 770, 500, 45);

			// Define a text content stream using the selected font, moving the
			// cursor and drawing the text "Hello World"
			contentStream.beginText();
			contentStream.setFont(font, 12);
			contentStream.moveTextPositionByAmount(40, 600);
			contentStream.drawString("Hello World");
			contentStream.endText();

			// Make sure that the content stream is closed:
			contentStream.close();

			PrinterJob printJob = PrinterJob.getPrinterJob();
			PrintService service = PrintServiceLookup.lookupDefaultPrintService();
			printJob.setPrintService(service);
			document.silentPrint(printJob);

		} catch (IOException | PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
