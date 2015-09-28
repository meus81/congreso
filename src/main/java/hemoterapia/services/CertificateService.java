package hemoterapia.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hemoterapia.domain.Certificate;

public class CertificateService {
	
	private static final String PERSISTENCE_UNIT_NAME = "congreso";
	private static EntityManagerFactory factory;
	
	public Certificate getCertificate(int id){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    EntityManager em = factory.createEntityManager();
		return (Certificate)em.createQuery("SELECT c FROM certificate c WHERE c.idCertificate = :id")
				 			  .setParameter(":id", id)
				              .getResultList()
				              .get(0);
	}
}