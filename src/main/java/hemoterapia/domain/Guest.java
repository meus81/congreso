package hemoterapia.domain;

import javax.inject.Singleton;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("G")
@Singleton
public class Guest extends Certificate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Transient
	private static Guest instance = null;

	protected Guest(){
	}
	
	public static Guest getInstance(){
		if (instance == null){
			instance = new Guest();
		}
		return instance;
	}
	
	public String toString(){
		return this.getName();
	}
}
