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
	private double taxWithLodgings2;
	private double taxWithoutLodgings;
	private double taxWithoutLodgings2;
	private double taxCompanions;
	private double taxCompanions2;
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

	public double getTaxWithLodgings2() {
		return taxWithLodgings2;
	}
	public void setTaxWithLodgings2(double taxWithLodgings) {
		this.taxWithLodgings2 = taxWithLodgings;
	}
	
	public double getTaxWithoutLodgings() {
		return taxWithoutLodgings;
	}
	public void setTaxWithoutLodgings(double taxWithoutLodgings) {
		this.taxWithoutLodgings = taxWithoutLodgings;
	}
	
	public double getTaxWithoutLodgings2() {
		return taxWithoutLodgings2;
	}
	public void setTaxWithoutLodgings2(double taxWithoutLodgings) {
		this.taxWithoutLodgings2 = taxWithoutLodgings;
	}
	
	public double getTaxCompanions() {
		return taxCompanions;
	}
	public void setTaxCompanions(double taxCompanions) {
		this.taxCompanions = taxCompanions;
	}
	
	public double getTaxCompanions2() {
		return taxCompanions2;
	}
	public void setTaxCompanions2(double taxCompanions) {
		this.taxCompanions2 = taxCompanions;
	}

	public String getName() {
		return this.name;
	}
	protected void setName(String aName) {
		this.name = aName;
	}

	public double getAmount(LodgingsType lodgingsType, int companionsTypeOne, int companionsTypeTwo) {
		return lodgingsType.getAmount(this, companionsTypeOne, companionsTypeTwo);
	}
	public double getAmountWithoutLodgings() {
		return this.getTaxWithoutLodgings();
	}
	public double getAmountWithoutLodgings2() {
		return this.getTaxWithoutLodgings2();
	}
	public abstract double getAmountWithLodgings(int companionsTypeOne, int companionsTypeTwo);
	public abstract double getAmountWithLodgings2(int companionsTypeOne, int companionsTypeTwo);
}