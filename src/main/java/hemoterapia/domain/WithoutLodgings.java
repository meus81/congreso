package hemoterapia.domain;

import java.io.Serializable;

import javax.inject.Singleton;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

public class WithoutLodgings extends LodgingsType{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public double getAmount(Certificate certificate, int companions) {
		return certificate.getAmountWithoutLodgings();
	}
}
