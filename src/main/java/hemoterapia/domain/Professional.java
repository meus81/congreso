package hemoterapia.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
@DiscriminatorValue("P")
public class Professional extends Certificate {
	@Transient
	private static Professional instance = null;
	
	protected Professional(){
		this.setTax(350.35);
	}
	
	public static Professional getInstance(){
		if (instance == null){
			instance = new Professional();
		}
		return instance;
	}
	
	public String toString(){
		return "Professional";
	}
}
