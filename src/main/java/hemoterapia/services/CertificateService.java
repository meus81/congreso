package hemoterapia.services;

import javax.persistence.EntityManager;

import hemoterapia.configuration.Application;
import hemoterapia.domain.Certificate;

public class CertificateService {

	public Certificate getCertificate(int id) {
		EntityManager em = Application.getInstance().getEntityManager();
		Certificate certificate = (Certificate) em
				.createQuery("SELECT c FROM Certificate c WHERE c.idCertificate = :id")
				.setParameter("id", id)
				.getSingleResult();

		System.out.println(certificate.getClass().getName());
//		System.out.println("El certificado obtenido es: " + certificate.toString() + " - Tax: " + certificate.getTax());
		
		return certificate;
	}
}