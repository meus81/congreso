package hemoterapia.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
@DiscriminatorValue("G")
public class Guest extends Certificate {

	@Transient
	private static Guest instance = null;

	protected Guest(){
		this.setTax(0.0);
	}
	
		public static Guest getInstance(){
		if (instance == null){
			instance = new Guest();
		}
		return instance;
	}
	
	public String toString(){
		return "Guest";
	}
}
