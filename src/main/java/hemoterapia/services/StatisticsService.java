package hemoterapia.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import hemoterapia.configuration.Application;
import hemoterapia.domain.Person;
import hemoterapia.statistics.Statistics;

public class StatisticsService {

	public Statistics getMainStatitics() {
		Application ap = Application.getInstance();
		EntityManager em = ap.getEntityManager();
		
		int professionalQty = ((Long) em.createQuery("SELECT count(*) FROM Person as p where p.certificate.idCertificate= 1").getSingleResult()).intValue();
		int professionalWithoutLodgingsOptionOneQty = ((Long) em.createQuery(	"SELECT count(*) " +
																		 		"FROM Person as p "+
																		 		"WHERE 	 p.certificate.idCertificate= 1 AND " +
																						"p.lodgings = 2").getSingleResult()).intValue();
		int professionalWithoutLodgingsOptionTwoQty = ((Long) em.createQuery(	"SELECT count(*) " +
																		 		"FROM Person as p "+
																		 		"WHERE   p.certificate.idCertificate= 1 AND " +
																		 				"p.lodgings = 4").getSingleResult()).intValue();
		int professionalWithLodgingsOptionOneQty = ((Long) em.createQuery(	"SELECT count(*) " +
																		 	"FROM Person as p "+
																		 	"WHERE   p.certificate.idCertificate= 1 AND " +
																	 				"p.lodgings = 1").getSingleResult()).intValue();
		int professionalWithLodgingsOptionTwoQty = ((Long) em.createQuery(	"SELECT count(*) " +
																		 	"FROM Person as p "+
																		 	"WHERE   p.certificate.idCertificate= 1 AND " +
																	 				"p.lodgings = 3").getSingleResult()).intValue();
		
		
		int technicianQty = ((Long) em.createQuery("SELECT count(*) FROM Person as p where p.certificate.idCertificate=2").getSingleResult()).intValue();
		int technicianWithoutLodgingsOptionOneQty = ((Long) em.createQuery(	"SELECT count(*) " +
																	 		"FROM Person as p "+
																	 		"WHERE 	 p.certificate.idCertificate= 2 AND " +
																					"p.lodgings = 2").getSingleResult()).intValue();
		int technicianWithoutLodgingsOptionTwoQty = ((Long) em.createQuery(	"SELECT count(*) " +
																	 		"FROM Person as p "+
																	 		"WHERE   p.certificate.idCertificate= 2 AND " +
																	 				"p.lodgings = 4").getSingleResult()).intValue();
		int technicianWithLodgingsOptionOneQty = ((Long) em.createQuery(	"SELECT count(*) " +
																		 	"FROM Person as p "+
																		 	"WHERE   p.certificate.idCertificate= 2 AND " +
																	 				"p.lodgings = 1").getSingleResult()).intValue();
		int technicianWithLodgingsOptionTwoQty = ((Long) em.createQuery(	"SELECT count(*) " +
																		 	"FROM Person as p "+
																		 	"WHERE   p.certificate.idCertificate= 2 AND " +
																	 				"p.lodgings = 3").getSingleResult()).intValue();		
		
		int guestQty = ((Long) em.createQuery("SELECT count(*) FROM Person as p where p.certificate.idCertificate=3").getSingleResult()).intValue();
		
		int companionsTypeOneQty = ((Long) em.createQuery("SELECT sum(p.companionsTypeOne) FROM Person as p").getSingleResult()).intValue();
		int companionsTypeTwoQty = ((Long) em.createQuery("SELECT sum(p.companionsTypeTwo) FROM Person as p").getSingleResult()).intValue();

		int totalPersons = professionalQty + technicianQty + guestQty + companionsTypeOneQty + companionsTypeTwoQty;
		double totalAmount = 0;

		Query all_persons = em.createQuery("SELECT p FROM Person p");
		List<Person> allPerson = (List<Person>) all_persons.getResultList();
		
		for (Person person : allPerson) {
			System.out.println(person.imprimiteLindo());
			totalAmount= totalAmount + person.getAmountToPaid();
		}
		
		Statistics result = new Statistics(professionalQty, professionalWithoutLodgingsOptionOneQty, professionalWithoutLodgingsOptionTwoQty, 
											professionalWithLodgingsOptionOneQty, professionalWithLodgingsOptionTwoQty,
											technicianQty, technicianWithoutLodgingsOptionOneQty, technicianWithoutLodgingsOptionTwoQty,
											technicianWithLodgingsOptionOneQty, technicianWithLodgingsOptionTwoQty, 
											guestQty, companionsTypeOneQty, companionsTypeTwoQty, totalPersons, totalAmount);
		
		return result;
	}

}
