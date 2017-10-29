package hemoterapia.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name="Con Alojamiento opcion 2", factoryMethod="getInstance")

public class WithLodgings2 extends LodgingsType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static WithLodgings2 _instance = null;

	private WithLodgings2(){
		
	}
	
	public static WithLodgings2 getInstance(){
		if (_instance == null){
			_instance = new WithLodgings2();
		}
		return _instance;
	}
	
	@Override
	public double getAmount(Certificate certificate, int companionsTypeOne, int companionsTypeTwo) {
		return certificate.getAmountWithLodgings2(companionsTypeOne, companionsTypeTwo);
	}
	
	@Override
	public String toString(){
		return "Con Alojamiento Opcion 2";
	}
}
