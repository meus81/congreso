package hemoterapia.domain;


import java.io.Serializable;

import javax.inject.Singleton;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@DiscriminatorValue("P")
@Singleton
public class Professional extends Certificate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	public Professional(){
	}

	@Override
	public String toString(){
		return this.getName();
	}

	@Override
	public double getAmountWithLodgings(int companions) {
//		CertificateService certificateService = new CertificateService();
//		certificateService.getCertificate(id)
		System.out.println("La tasa a pagar es: " + this.getTaxWithLodgings() + " + " + companions + " * " + this.getTaxCompanions());
		return this.getTaxWithLodgings() + companions * this.getTaxCompanions();
	}
}
