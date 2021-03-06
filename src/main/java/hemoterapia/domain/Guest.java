package hemoterapia.domain;

import javax.inject.Singleton;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@DiscriminatorValue("G")
@Singleton
public class Guest extends Certificate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Guest(){
	}
	
	@Override
	public String toString(){
		return this.getName();
	}

	@Override
	public double getAmountWithLodgings(int companionsTypeOne, int companionsTypeTwo) {
		return this.getTaxWithoutLodgings();
	}

	@Override
	public double getAmountWithLodgings2(int companionsTypeOne, int companionsTypeTwo) {
		return this.getTaxWithoutLodgings2();
	}
}
