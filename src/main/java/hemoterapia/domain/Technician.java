package hemoterapia.domain;

import javax.inject.Singleton;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("T")
@Singleton
public class Technician extends Certificate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Transient
	private static Technician instance = null;

	protected Technician(){
	}
	
	public static Technician getInstance(){
		if (instance == null){
			instance = new Technician();
		}
		return instance;
	}
	
	public String toString(){
		return this.getName();
	}
}
