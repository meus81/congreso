package hemoterapia.domain;

public abstract class LodgingsType {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public abstract double getAmount(Certificate certificate, int companions);

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