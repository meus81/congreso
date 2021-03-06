package hemoterapia.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({WithLodgings.class, WithoutLodgings.class, WithLodgings2.class, WithoutLodgings2.class})
public abstract class LodgingsType implements Serializable{

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	
	public abstract double getAmount(Certificate certificate, int companionsTypeOne, int companionsTypeTwo);

	/**
	 * Returns true if the Object's class name matches this Object's class name.
	 */
	public final boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		return getClass().getName().equals(obj.getClass().getName());
	}

	/**
	 * Returns the hashCode of the Object's Class name because all instances of
	 * this Class are equal.
	 */
	public final int hashCode() {
		return getClass().getName().hashCode();
	}
}