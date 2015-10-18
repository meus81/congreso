package hemoterapia.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "certificate_type", discriminatorType=DiscriminatorType.STRING)
public abstract class Certificate implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int idCertificate;
	
	private double tax;
	private String name;
	
		
	protected int getIdCertificate() {
		return idCertificate;
	}
	protected void setIdCertificate(int idCertificate) {
		this.idCertificate = idCertificate;
	}
	
	public double getTax() {
		return tax;
	}
	protected void setTax(double tax) {
		this.tax = tax;
	}
	
	public String getName(){
		return this.name;
	}
	protected void setName(String aName){
		this.name = aName;
	}
	
	public double getAmount(){
		return this.getTax();
	}
}