package hemoterapia.print;

import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.log4j.Logger;
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

	public static Logger log = Logger.getLogger("fileAppender");

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

	private void drawHeaderPDF(PDDocument doc, PDPage page, PDRectangle mediabox, InputStream headerImage)
			throws IOException {
		BufferedImage tmp_image;
		try {
			tmp_image = ImageIO.read(headerImage);
			BufferedImage image = new BufferedImage(tmp_image.getWidth(), tmp_image.getHeight(),
					BufferedImage.TYPE_4BYTE_ABGR);
			image.createGraphics().drawRenderedImage(tmp_image, null);
			PDXObjectImage logoImage = new PDPixelMap(doc, image);

			float margin = 30;
			float headerX = mediabox.getLowerLeftX() + margin;
			float headerY = mediabox.getUpperRightY() - margin - 40;
			float headerWidth = 140;
			float headerHeight = 40;

			// Si lo queremos rotar
			// float margin = 50;
			// float headerX = mediabox.getLowerLeftY() + margin;
			// float headerY = mediabox.getUpperRightX() - margin - 10;
			// float headerWidth = mediabox.getHeight() - 2 * margin;
			// float headerHeight = 40;

			PDPageContentStream contentStreamDraw = new PDPageContentStream(doc, page, true, true);
			// si lo queremos rotar a landscape
			// contentStreamDraw.concatenate2CTM(cos 90°, sen 90°, -sen 90°, cos
			// 90°, 0, 0);
			// contentStreamDraw.concatenate2CTM(0, 1, -1, 0,
			// mediabox.getWidth(),
			// 0);
			contentStreamDraw.drawXObject(logoImage, headerX, headerY, headerWidth, headerHeight);
			contentStreamDraw.drawLine(mediabox.getLowerLeftX() + 25, headerY - 8, mediabox.getUpperRightX() - 25,
					headerY - 8);
			contentStreamDraw.close();
		} catch (IOException e) {
			try {
				tmp_image = ImageIO.read(new File(
						"C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\congreso\\img\\nuevoLogo.png"));
				BufferedImage image = new BufferedImage(tmp_image.getWidth(), tmp_image.getHeight(),
						BufferedImage.TYPE_4BYTE_ABGR);
				image.createGraphics().drawRenderedImage(tmp_image, null);
				PDXObjectImage logoImage = new PDPixelMap(doc, image);

				float margin = 30;
				float headerX = mediabox.getLowerLeftX() + margin;
				float headerY = mediabox.getUpperRightY() - margin - 40;
				float headerWidth = 140;
				float headerHeight = 40;

				// Si lo queremos rotar
				// float margin = 50;
				// float headerX = mediabox.getLowerLeftY() + margin;
				// float headerY = mediabox.getUpperRightX() - margin - 10;
				// float headerWidth = mediabox.getHeight() - 2 * margin;
				// float headerHeight = 40;

				PDPageContentStream contentStreamDraw = new PDPageContentStream(doc, page, true, true);
				// si lo queremos rotar a landscape
				// contentStreamDraw.concatenate2CTM(cos 90°, sen 90°, -sen 90°,
				// cos
				// 90°, 0, 0);
				// contentStreamDraw.concatenate2CTM(0, 1, -1, 0,
				// mediabox.getWidth(),
				// 0);
				contentStreamDraw.drawXObject(logoImage, headerX, headerY, headerWidth, headerHeight);
				contentStreamDraw.drawLine(mediabox.getLowerLeftX() + 25, headerY - 8, mediabox.getUpperRightX() - 25,
						headerY - 8);
				contentStreamDraw.close();
			} catch (IOException e1) {
				float margin = 30;
				float headerY = mediabox.getUpperRightY() - margin - 40;
				PDPageContentStream contentStreamDraw = new PDPageContentStream(doc, page, true, true);
				contentStreamDraw.drawLine(mediabox.getLowerLeftX() + 25, headerY - 8, mediabox.getUpperRightX() - 25,
						headerY - 8);
				contentStreamDraw.close();
			}
		}
	}

	private String createTextTicket(Person person) {
		String completeName = person.getName() + " " + person.getSurname();
		Double total2Pay = person.getAmountToPaid();
		DecimalFormat df = new DecimalFormat("#.00");

		return "Recibí de " + completeName + " la suma de pesos " + df.format(total2Pay)
				+ " en concepto de inscripción al 29° encuentro provincial" + " de hemoterapia.";
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

	private void putTextInDiferentsLines(PDDocument doc, PDPage page, PDRectangle mediabox, PDFont pdfFont,
			float fontSize, float leading, String text, float width, float startX, float startY) throws IOException {

		PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true);
		List<String> lines = getTextInLines(text, pdfFont, fontSize, width);

		contentStream.beginText();
		contentStream.setFont(pdfFont, fontSize);
		contentStream.moveTextPositionByAmount(startX, startY);
		for (String line : lines) {
			contentStream.drawString(line);
			contentStream.moveTextPositionByAmount(0, -leading);
		}
		contentStream.endText();
		contentStream.close();
	}

	public void printRegistrationTicket(Person person, boolean print, InputStream headerImage, String pathToSave) {
		PDDocument doc = null;
		try {
			doc = createDoc();
			PDPage page = setPage(PDPage.PAGE_SIZE_A5);
			// si queremos rotar a ladnscape la hoja
			// page.setRotation(90);
			doc.addPage(page);

			PDRectangle mediabox = page.findMediaBox();
			drawHeaderPDF(doc, page, mediabox, headerImage);

			PDFont pdfFont = PDType1Font.HELVETICA;
			float fontSize = 10;
			float leading = 1.5f * fontSize;

			float margin = 50;
			float width = mediabox.getWidth() - 2 * margin;
			float startX = mediabox.getLowerLeftX() + margin;
			float startY = mediabox.getUpperRightY() - margin - 100;
			// si lo queremos rotar a 90°
			// float startX = mediabox.getLowerLeftY() + margin;
			// float startY = mediabox.getUpperRightX() - margin - 10;
			// float width = mediabox.getHeight() - 2 * margin;

			String textTicket = createTextTicket(person);
			putTextInDiferentsLines(doc, page, mediabox, pdfFont, fontSize, leading, textTicket, width, startX, startY);

			String textFooter = "Encuentro para la Organización y Administración de la Hemoterapia de la Provincia de Buenos Aires";
			List<String> lines = getTextInLines(textFooter, pdfFont, fontSize, width);

			// putTextInDiferentsLines(doc, page, mediabox,
			// PDType1Font.HELVETICA, 9, leading, textTile, width,
			// startX, startY);
			int marginTop = 510;
			PDPageContentStream stream = new PDPageContentStream(doc, page, true, true);
			fontSize = 9; // Or whatever font size you want.
			float titleWidth = pdfFont.getStringWidth(textFooter) / 1000 * fontSize;
			float titleHeight = pdfFont.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;

			stream.beginText();
			stream.setFont(pdfFont, fontSize);
			float anteriorX = 0;
			float anteriorY = 0;
			for (String line : lines) {
				titleWidth = pdfFont.getStringWidth(line) / 1000 * fontSize;
				stream.moveTextPositionByAmount(((mediabox.getWidth() - titleWidth) / 2) - anteriorX,
						mediabox.getHeight() - marginTop - anteriorY);
				stream.drawString(line);
				anteriorX = (mediabox.getWidth() - titleWidth) / 2;
				anteriorY = mediabox.getHeight() - marginTop;
				marginTop = marginTop + 15;
				System.out.println(
						"medidas:" + mediabox.getHeight() + "-" + marginTop + "-" + titleHeight + "-" + leading);
			}
			stream.endText();
			stream.close();

			PDPageContentStream contentStreamDraw = new PDPageContentStream(doc, page, true, true);
			contentStreamDraw.drawLine(mediabox.getLowerLeftX() + 20, mediabox.getUpperRightY() - 530,
					mediabox.getUpperRightX() - 20, mediabox.getUpperRightY() - 530);
			contentStreamDraw.close();

			marginTop = 535;
			startY = mediabox.getUpperRightX() - margin;
			String textFooter2 = "Villa Gesell 4, 5 y 6 de noviembre de 2015";
			// putTextInDiferentsLines(doc, page, mediabox,
			// PDType1Font.HELVETICA, 8, leading, textTile, width,
			// startX, startY);

			PDPageContentStream stream2 = new PDPageContentStream(doc, page, true, true);
			fontSize = 8; // Or whatever font size you want.
			titleWidth = pdfFont.getStringWidth(textFooter2) / 1000 * fontSize;
			titleHeight = pdfFont.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;

			stream2.beginText();
			stream2.setFont(pdfFont, fontSize);
			stream2.moveTextPositionByAmount((mediabox.getWidth() - titleWidth) / 2,
					mediabox.getHeight() - marginTop - titleHeight);
			stream2.drawString(textFooter2);
			stream2.endText();
			stream2.close();

			if (print) {
				PrinterJob printJob = PrinterJob.getPrinterJob();
				PrintService service = PrintServiceLookup.lookupDefaultPrintService();
				System.out.println("====================================");
				System.out.println("IMPRESORAAAAAAAAAAAAAA");
				System.out.println(service);
				System.out.println("====================================");
				if (service != null) {
					printJob.setPrintService(service);
					doc.silentPrint(printJob);
				}
			}

			
			doc.save(pathToSave + person.getIdPerson() + "-" + person.getName() + "-" + person.getSurname() + ".pdf");
			doc.close();

		} catch (IOException | COSVisitorException | PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
