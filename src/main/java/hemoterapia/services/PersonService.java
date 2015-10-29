package hemoterapia.services;

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

	public void save(Person person) {
		Application ap = Application.getInstance();
		EntityManager em = ap.getEntityManager();
		em.getTransaction().begin();
		em.persist(person);
		em.getTransaction().commit();
		
		Printer printer = new Printer();
		printer.printRegistrationTicket(person, true);
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

}
