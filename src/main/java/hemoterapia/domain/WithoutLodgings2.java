package hemoterapia.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name="Sin Alojamiento opcion 2", factoryMethod="getInstance")
public class WithoutLodgings2 extends LodgingsType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static WithoutLodgings2 _instance = null;

	private WithoutLodgings2(){
	}
	
	public static WithoutLodgings2 getInstance(){
		if (_instance == null){
			_instance = new WithoutLodgings2();
		}
		return _instance;
	}
	
	@Override
	public double getAmount(Certificate certificate, int companionsTypeOne, int companionsTypeTwo) {
		return certificate.getAmountWithoutLodgings2();
	}
	
	@Override
	public String toString(){
		return "Sin Alojamiento opcion 2";
	}
}
