package hemoterapia.domain;

import javax.inject.Singleton;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("T")
@Singleton
public class Technician extends Certificate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Technician(){
	}
	
	@Override
	public String toString(){
		return this.getName();
	}
}
