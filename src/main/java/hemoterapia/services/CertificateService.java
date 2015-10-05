package hemoterapia.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hemoterapia.domain.Certificate;

public class CertificateService {

	private static final String PERSISTENCE_UNIT_NAME = "congreso";
	private static EntityManagerFactory factory;

	public Certificate getCertificate(int id) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		Certificate certificate = (Certificate) em
				.createQuery("SELECT c FROM Certificate c WHERE c.idCertificate = :id")
				.setParameter("id", id)
				.getSingleResult();

		System.out.println(certificate.getClass().getName());
		System.out.println("El certificado obtenido es: " + certificate.toString() + " - Tax: " + certificate.getTax());
		
		return certificate;
	}
}