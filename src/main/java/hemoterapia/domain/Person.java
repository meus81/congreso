package hemoterapia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table
public class Person{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int idPerson;
	private String name;
	private String surname;
	
	public int getIdPerson(){
		return idPerson;
	}
	
	public void setIdPerson(int id){
		idPerson= id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
}