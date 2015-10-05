package hemoterapia.domain;

import hemoterapia.domain.Certificate;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.glassfish.jersey.linking.InjectLink;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table
public class Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int idPerson;
	private String name;
	private String surname;
	private int companions;
	
//	@InjectLink("/certificate/{îd}")
	@ManyToOne(optional=false)
	@JoinColumn(name="idCertificate", nullable=false, updatable=false)
	private Certificate title;
	
//	private LodgingType lodgings;
	
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
	public int getCompanions() {
		return companions;
	}
	public void setCompanions(int companions) {
		this.companions = companions;
	}
	public Certificate getTitle() {
		return title;
	}
	public void setTitle(Certificate title) {
		this.title = title;
	}
}