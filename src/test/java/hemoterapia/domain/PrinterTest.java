package hemoterapia.domain;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PrinterTest {
	private PDDocument createDoc() {
		return new PDDocument();
	}

	private PDPage setPage(PDRectangle size) {
		return new PDPage(size);
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

	public void imprimir() {

		PDDocument doc = null;
		try {
			doc = createDoc();
			PDPage page = setPage(PDPage.PAGE_SIZE_A5);
			doc.addPage(page);

			PDRectangle mediabox = page.findMediaBox();
			PDFont pdfFont = PDType1Font.HELVETICA;
			float fontSize = 10;
			float leading = 1.5f * fontSize;

			float margin = 50;
			float width = mediabox.getWidth() - 2 * margin;
			float startX = mediabox.getLowerLeftX() + margin;
			float startY = mediabox.getUpperRightY() - margin - 100;

			String textTicket = "Esto es una prueba para saber si la imprersora imprime";
			putTextInDiferentsLines(doc, page, mediabox, pdfFont, fontSize, leading, textTicket, width, startX, startY);

			String textFooter = "Encuentro para la Organización y Administración de la Hemoterapia de la Provincia de Buenos Aires";
			List<String> lines = getTextInLines(textFooter, pdfFont, fontSize, width);

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

			doc.close();

		} catch (IOException | PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		
		PrinterTest printerTest= new PrinterTest(); 
		printerTest.imprimir();
	}

}
