package hemoterapia.configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.glassfish.jersey.server.ResourceConfig;

public class Application {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("congreso");
	private EntityManager em = emf.createEntityManager();
	private static Application _instance = null;
	final ResourceConfig application = new ResourceConfig();
	
	private Application(){
	}
	
	public static Application getInstance(){
		if (_instance == null){
			_instance = new Application();
			
		}
		return _instance;
	}
	
	public EntityManager getEntityManager(){
		return _instance.em;
	}
}
