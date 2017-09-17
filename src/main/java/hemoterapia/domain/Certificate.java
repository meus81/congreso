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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "certificate_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Certificate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int idCertificate;

	private double taxWithLodgings;
	private double taxWithoutLodgings;
	private double taxCompanions;
	private String name;

	public int getIdCertificate() {
		return idCertificate;
	}
	protected void setIdCertificate(int idCertificate) {
		this.idCertificate = idCertificate;
	}
	
	public double getTaxWithLodgings() {
		return taxWithLodgings;
	}
	public void setTaxWithLodgings(double taxWithLodgings) {
		this.taxWithLodgings = taxWithLodgings;
	}

	public double getTaxWithoutLodgings() {
		return taxWithoutLodgings;
	}
	public void setTaxWithoutLodgings(double taxWithoutLodgings) {
		this.taxWithoutLodgings = taxWithoutLodgings;
	}

	public double getTaxCompanions() {
		return taxCompanions;
	}
	public void setTaxCompanions(double taxCompanions) {
		this.taxCompanions = taxCompanions;
	}

	public String getName() {
		return this.name;
	}
	protected void setName(String aName) {
		this.name = aName;
	}

	public double getAmount(LodgingsType lodgingsType, int companions) {
		return lodgingsType.getAmount(this, companions);
	}
	public double getAmountWithoutLodgings() {
		return this.getTaxWithoutLodgings();
	}
	public abstract double getAmountWithLodgings(int companions);
}