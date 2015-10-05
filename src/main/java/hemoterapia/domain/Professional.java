package hemoterapia.domain;


import javax.inject.Singleton;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("P")
@Singleton
public class Professional extends Certificate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Transient
	private static Professional instance = null;
	
	protected Professional(){
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
