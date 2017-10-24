package hemoterapia.domain;

import javax.servlet.ServletException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import hemoterapia.services.PersonService;
import junit.framework.TestCase;

public class PersonTest extends TestCase {
	

	// @Test
	// public void testSavePerson() {
	// CertificateService certificateService = new CertificateService();
	// Professional professional = (Professional)
	// certificateService.getCertificate(1);
	//
	// PersonService personService = new PersonService();
	// Person personSave = new Person();
	// personSave.setName("Sole");
	// personSave.setSurname("Sanchez");
	// personSave.setCompanions(3);
	// personSave.setCertificate(professional);
	// personService.save(personSave);
	//
	// Person personGet = personService.getPerson(1);
	// System.out.println("la persona es: " + personGet.getName() + "-" +
	// personGet.getSurname() + "-" +
	// personGet.getCertificate().toString());
	// }

	@Test
	public void testGetPerson() throws ServletException {
		// Logger root = Logger.getRootLogger();
		// if (!root.getAllAppenders().hasMoreElements()) {
		// System.out.println("tiene apenders");
		// }
		// Logger log = Logger.getLogger(PersonTest.class);
		Logger logger = LoggerFactory.getLogger("fileAppender");

		logger.debug("Hello World");
		logger.error("laputa que te pario");

		PersonService personService = new PersonService();
		Person p = personService.getPerson(10);
		logger.debug("Hola como estas");

		System.out.println("la persona es: " + p.getName() + "-" + p.getSurname() + "-" + p.getCertificate().toString()
				+ "-" + p.getLodgings());
	}
}
