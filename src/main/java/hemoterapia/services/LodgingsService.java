package hemoterapia.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import hemoterapia.domain.LodgingsType;

public class LodgingsService {

	public LodgingsType getLodgingsType(String subClassName) {
		Class<LodgingsType> c;
		LodgingsType lodgingsType = null;
		try {
			c = (Class<LodgingsType>) Class.forName(subClassName);
			Method factoryMethod = c.getDeclaredMethod("getInstance", null);
			lodgingsType = (LodgingsType) factoryMethod.invoke(null, null);;
		} catch (ClassNotFoundException | IllegalAccessException 
				| NoSuchMethodException | SecurityException 
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lodgingsType;
	}
}