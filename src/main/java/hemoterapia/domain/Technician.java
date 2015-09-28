package hemoterapia.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
@DiscriminatorValue("T")
public class Technician extends Certificate {
	@Transient
	private static Technician instance = null;

	protected Technician(){
		this.setTax(233.85);
	}
	
	public static Technician getInstance(){
		if (instance == null){
			instance = new Technician();
		}
		return instance;
	}
	
	public String toString(){
		return "Technician";
	}
}
