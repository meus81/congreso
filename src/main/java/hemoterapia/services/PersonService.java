package hemoterapia.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
		Application ap = Application.getInstance();
		EntityManager em = ap.getEntityManager();
		em.getTransaction().begin();
		em.persist(person);
		em.getTransaction().commit();
	}
}
