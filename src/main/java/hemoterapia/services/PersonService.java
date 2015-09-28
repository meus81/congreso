package hemoterapia.services;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hemoterapia.domain.Person;

public class PersonService {

	private static final String PERSISTENCE_UNIT_NAME = "congreso";
	private static EntityManagerFactory factory;
		
	public Person getDefaultPerson(){
		Person person = new Person();
		person.setName("Rodrigo");
		person.setSurname("Rolón Luna");
		return person;
	}
	
	public List<Person> getAllPersons(){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    EntityManager em = factory.createEntityManager();
	    
		Query query = em.createQuery("SELECT p FROM Person p");
	    List<Person> allPerson = (List<Person>)query.getResultList();
		return allPerson;
	}

	public void save(Person person) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    EntityManager entity = factory.createEntityManager();
		entity.getTransaction().begin();
		entity.persist(person);
		entity.getTransaction().commit();
	}
}
