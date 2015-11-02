package hemoterapia.domain;

import org.junit.Test;

import hemoterapia.services.CertificateService;
import junit.framework.TestCase;

public class CertificateTest extends TestCase {

	@Test
	public void testGetCertificate() {
		CertificateService certificateService = new CertificateService();
		Certificate professional = certificateService.getCertificate(1);
		System.out.println(professional.toString());
		assertTrue(professional.toString().equalsIgnoreCase("Profesional"));
		Certificate technical = certificateService.getCertificate(2);
		assertTrue(technical.toString().equalsIgnoreCase("Tecnico"));
		Certificate guest = certificateService.getCertificate(3);
		assertTrue(guest.toString().equalsIgnoreCase("Invitado"));
	}
}
