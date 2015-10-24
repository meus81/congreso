package hemoterapia.domain;

import org.junit.Test;

import hemoterapia.services.CertificateService;
import hemoterapia.services.PersonService;
import junit.framework.TestCase;

public class PersonTest extends TestCase {
  
//	@Test
//	public void testSavePerson() {
//		CertificateService certificateService = new CertificateService();
//		Professional professional = (Professional) certificateService.getCertificate(1);
//		
//		PersonService personService = new PersonService();
//		Person personSave = new Person();
//		personSave.setName("Sole");
//		personSave.setSurname("Sanchez");
//		personSave.setCompanions(3);
//		personSave.setCertificate(professional);
//		personService.save(personSave);
//		
//		Person personGet = personService.getPerson(1);
//		System.out.println("la persona es: " + personGet.getName() + "-" + 
//							personGet.getSurname() + "-" + 
//							personGet.getCertificate().toString());
//	}
	
	@Test
	public void testGetPerson(){
		PersonService personService = new PersonService();
		Person p = personService.getPerson(7);
		
		System.out.println("la persona es: " + p.getName() + "-" + 
				p.getSurname() + "-" + 
				p.getCertificate().toString() + "-" + 
				p.getLodgings());
	}
}
