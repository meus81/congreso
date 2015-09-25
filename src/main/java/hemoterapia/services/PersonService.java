package hemoterapia.services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hemoterapia.domain.Person;

public class PersonService {

	private static final String PERSISTENCE_UNIT_NAME = "congreso";
	private static EntityManagerFactory factory;
	private EntityManager entity;
		
	public Person getDefaultPerson(){
		Person person = new Person();
		person.setName("Rodrigo");
		person.setSurname("Rolón Luna");
		return person;
	}
	
	public List<Person> getAllPersons(){
		List<Person> allPersons = new ArrayList<Person>();
		
		Person person1 = new Person();
		person1.setName("Rodrigo");
		person1.setSurname("Rolón Luna");
		allPersons.add(person1);
		
		Person person2 = new Person();
		person2.setName("Luisina");
		person2.setSurname("Rolón Luna");
		allPersons.add(person2);
		
		Person person3 = new Person();
		person3.setName("Lisandro");
		person3.setSurname("Rolón Luna");
		allPersons.add(person3);
		
		Person person4 = new Person();
		person4.setName("Ramiro");
		person4.setSurname("Rolón Luna");
		allPersons.add(person4);
		
		return allPersons;
	}

	public void save(Person person) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    EntityManager entity = factory.createEntityManager();
		entity.getTransaction().begin();
		entity.persist(person);
		entity.getTransaction().commit();
	}
}
