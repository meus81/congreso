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
		
		int professionalQTy = ((Long) em.createQuery("SELECT count(*) FROM Person as p where p.certificate.idCertificate= 1").getSingleResult()).intValue();
		int technicianQTy = ((Long) em.createQuery("SELECT count(*) FROM Person as p where p.certificate.idCertificate=2").getSingleResult()).intValue();
		int guestQTy = ((Long) em.createQuery("SELECT count(*) FROM Person as p where p.certificate.idCertificate=3").getSingleResult()).intValue();
		int companionsQty = ((Long) em.createQuery("SELECT sum(p.companions) FROM Person as p").getSingleResult()).intValue();
		
		int inscriptsWithLodgingsQty = ((Long) em.createQuery("SELECT count(*) FROM Person as p WHERE p.lodgings = 1").getSingleResult()).intValue();
		int personsWithLodgingsQty = companionsQty + inscriptsWithLodgingsQty;
				
		int totalPersons = professionalQTy+technicianQTy+guestQTy+companionsQty;
		double totalAmount = 0;
		
		Query all_persons = em.createQuery("SELECT p FROM Person p");
		List<Person> allPerson = (List<Person>) all_persons.getResultList();
		
		for (Person person : allPerson) {
			System.out.println(person.imprimiteLindo());
			totalAmount= totalAmount + person.getAmountToPaid();
		}
		
		Statistics result = new Statistics(professionalQTy, technicianQTy, guestQTy, companionsQty, personsWithLodgingsQty, totalPersons, totalAmount);
		
		return result;
	}

}
