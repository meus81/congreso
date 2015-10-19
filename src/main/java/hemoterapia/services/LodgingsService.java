package hemoterapia.services;

import hemoterapia.domain.LodgingsType;
import hemoterapia.domain.WithoutLodgings;

public class LodgingsService {

	public LodgingsType getLodgingsType(String subClassName) {
		Class<LodgingsType> c;
		LodgingsType lodgingsType = new WithoutLodgings();
		try {
			c = (Class<LodgingsType>) Class.forName(subClassName);
			lodgingsType = (LodgingsType) c.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lodgingsType;
	}
}