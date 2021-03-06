package hemoterapia.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name="Sin Alojamiento opcion 1", factoryMethod="getInstance")
public class WithoutLodgings extends LodgingsType{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static WithoutLodgings _instance = null;

	private WithoutLodgings(){
	}
	
	public static WithoutLodgings getInstance(){
		if (_instance == null){
			_instance = new WithoutLodgings();
		}
		return _instance;
	}
	
	@Override
	public double getAmount(Certificate certificate, int companionsTypeOne, int companionsTypeTwo) {
		return certificate.getAmountWithoutLodgings();
	}
	
	@Override
	public String toString(){
		return "Sin Alojamiento Opcion 1";
	}
}
