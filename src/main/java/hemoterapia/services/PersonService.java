package hemoterapia.services;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import hemoterapia.configuration.Application;
import hemoterapia.domain.Person;
import hemoterapia.domain.Professional;

public class PersonService {

	public Person getDefaultPerson() {
		Person person = new Person();
		person.setName("Rodrigo");
		person.setSurname("Rolón Luna");
		return person;
	}

	public List<Person> getAllPersons() {
		Application ap = Application.getInstance();
		EntityManager em = ap.getEntityManager();
		
		Query query = em.createQuery("SELECT p FROM Person p");
		List<Person> allPerson = (List<Person>) query.getResultList();
		return allPerson;
	}

	public Person getPerson(int id) {
		Application ap = Application.getInstance();
		EntityManager em = ap.getEntityManager();
		Person p = (Person) em.createQuery("SELECT p FROM Person p where p.id = :id")
							  .setParameter("id", id)
				              .getSingleResult();
		return p;
	}

	public void save(Person person) {
//		Application ap = Application.getInstance();
//		EntityManager em = ap.getEntityManager();
//		em.getTransaction().begin();
//		em.persist(person);
//		em.getTransaction().commit();
		
		System.out.println("VOY a IMPRIMIR......");
		// Create a document and add a page to it
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage( page );

		// Create a new font object selecting one of the PDF base fonts
		PDFont font = PDType1Font.HELVETICA_BOLD;

		// Start a new content stream which will "hold" the to be created content
		PDPageContentStream contentStream;
		try {
			contentStream = new PDPageContentStream(document, page);
			// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
			contentStream.beginText();
			contentStream.setFont( font, 12 );
			contentStream.moveTextPositionByAmount( 100, 700 );
			contentStream.drawString( "Hello World" );
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
