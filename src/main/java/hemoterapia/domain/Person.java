package hemoterapia.domain;

import hemoterapia.domain.Certificate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.Type;

/**
 * @author meus
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPerson;
	private String name;
	private String surname;
	private String email;
	private String address;
	private int companionsTypeOne;
	private int companionsTypeTwo;

	@XmlElement(name = "certificate", required = true)
	@ManyToOne(optional = false)
	@JoinColumn(name = "idCertificate", nullable = false, updatable = false)
	private Certificate certificate;

	@Column(name = "lodgings")
	@Type(type = "hemoterapia.configuration.LodgingsUserType")
	private LodgingsType lodgings;

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int id) {
		idPerson = id;
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

	public int getCompanionsTypeOne() {
		return companionsTypeOne;
	}

	public void setCompanionsTypeOne(int companions) {
		this.companionsTypeOne = companions;
	}

	public int getCompanionsTypeTwo() {
		return companionsTypeTwo;
	}

	public void setCompanionsTypeTwo(int companions) {
		this.companionsTypeTwo = companions;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public LodgingsType getLodgings() {
		return lodgings;
	}

	public void setLodgings(LodgingsType lodgings) {
		this.lodgings = lodgings;
	}

	public double getAmountToPaid() {
		return this.getCertificate().getAmount(this.getLodgings(), this.getCompanionsTypeOne(), this.getCompanionsTypeTwo());
	}

	public String imprimiteLindo() {
		return this.getName() + " - titulo: " + this.getCertificate() + " - acompañantes: "  + this.getCompanionsTypeOne()  + " - Alojamiento: " + this.getLodgings();
	}
}