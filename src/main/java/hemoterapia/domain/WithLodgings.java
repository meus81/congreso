package hemoterapia.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name="Con Alojamiento opcion 1", factoryMethod="getInstance")
public class WithLodgings extends LodgingsType{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static WithLodgings _instance = null;

	private WithLodgings(){
		
	}
	
	public static WithLodgings getInstance(){
		if (_instance == null){
			_instance = new WithLodgings();
		}
		return _instance;
	}
	
	@Override
	public double getAmount(Certificate certificate, int companionsTypeOne, int companionsTypeTwo) {
		return certificate.getAmountWithLodgings(companionsTypeOne, companionsTypeTwo);
	}
	
	@Override
	public String toString(){
		return "Con Alojamiento Opcion 1";
	}
	
	
}
