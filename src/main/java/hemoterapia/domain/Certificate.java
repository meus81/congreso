package hemoterapia.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "certificate_type")
public abstract class Certificate {
	
	@Id
	private int idCertificate;
	private double tax;

	public double getTax() {
		return tax;
	}

	protected void setTax(double tax) {
		this.tax = tax;
	}
}
