package hemoterapia.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hemoterapia.domain.Certificate;
import hemoterapia.services.CertificateService;

@ApplicationPath("/resources")
@Path("/")
public class CertificateRestService {
	
	@GET
	@Path("/certificate/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Certificate getCertificate(@PathParam("id") int id ){
		System.out.println("El id del parametro " + id);
		CertificateService personService = new CertificateService();
		return personService.getCertificate(id);
	}
}
