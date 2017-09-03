package hemoterapia.services;

import java.io.InputStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import hemoterapia.configuration.Application;
import hemoterapia.domain.Person;
import hemoterapia.print.Printer;

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

	public List<Person> getPersons(String name, String surname) {
		Application ap = Application.getInstance();
		EntityManager em = ap.getEntityManager();
		
		System.out.println("Imprimiendo los stings de busqueda: " + name + " - " + surname);
		
		name = (name!=null)? name : "";
		surname = (surname!=null)? surname : "";
		
		Query query = em.createQuery("SELECT p FROM Person p WHERE p.name like :name and p.surname like :surname")
				.setParameter("name", "%"+name+"%").
				setParameter("surname", "%"+surname+"%");
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

	public String save(Person person, InputStream headerImage, String pathToSave) {
		Application ap = Application.getInstance();
		EntityManager em = ap.getEntityManager();
		em.getTransaction().begin();
		em.persist(person);
		em.getTransaction().commit();
		
		Printer printer = new Printer();
		printer.printRegistrationTicket(person, false, headerImage, pathToSave);
		
		double amount= person.getAmountToPaid();
		
		return "{amount: "+ new Double(amount).toString() + "}";
	}

	public int deletePerson(int id) {
		Application ap = Application.getInstance();
		EntityManager em = ap.getEntityManager();
		em.getTransaction().begin();
		int result = em.createQuery("DELETE FROM Person p where p.id = :id")
							  .setParameter("id", id)
				              .executeUpdate();
		em.getTransaction().commit();
		System.out.println("Rows affected: " + result);
		return result;
	}
	
	public int modifyPerson(int id, Person person) {
		Application ap = Application.getInstance();
		EntityManager em = ap.getEntityManager();
		em.getTransaction().begin();

		int result = em.createQuery("UPDATE Person SET name= :name , surname = :surname, "
									+ "companions= :companions, idCertificate= :idCertificate, "
									+ "address= :address, email= :email, lodgings= :lodgings "
									+ "WHERE p.id = :id")
						.setParameter("name", person.getName())
						.setParameter("surname", person.getSurname())
						.setParameter("companions", person.getCompanions())
						.setParameter("idCertificate", person.getCertificate())
						.setParameter("address", person.getAddress())
						.setParameter("email", person.getEmail())
						.setParameter("lodgings", person.getLodgings())
						.executeUpdate();
		em.getTransaction().commit();
		
		return result;
	}

}
