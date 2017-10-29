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
	public double getAmountWithLodgings(int companionsTypeOne, int companionsTypeTwo) {
		double total = this.getTaxWithLodgings() + companionsTypeOne * this.getTaxCompanions() + companionsTypeTwo * this.getTaxCompanions2();
		System.out.println("La tasa a pagar es: " + this.getTaxWithLodgings() + " + " + 
							companionsTypeOne + " * " + this.getTaxCompanions() + " + " +
							companionsTypeTwo + " * " + this.getTaxCompanions2() +  "= " + total );
		return total;
	}

	@Override
	public double getAmountWithLodgings2(int companionsTypeOne, int companionsTypeTwo) {
		double total = this.getTaxWithLodgings2() + companionsTypeOne * this.getTaxCompanions() + companionsTypeTwo * this.getTaxCompanions2();
		System.out.println("La tasa a pagar es: " + this.getTaxWithLodgings2() + " + " 
							+ companionsTypeOne + " * " + this.getTaxCompanions() + " + "
							+ companionsTypeTwo + " * " + this.getTaxCompanions2() +  "= " + total );
		return total;
	}
}
