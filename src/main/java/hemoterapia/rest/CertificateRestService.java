package hemoterapia.rest;

import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import hemoterapia.domain.Certificate;
import hemoterapia.domain.Person;
import hemoterapia.services.CertificateService;
import hemoterapia.services.PersonService;

@ApplicationPath("/resources")
@Path("/")
public class CertificateRestService {
	@GET
	@Path("/certifiacte/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Certificate getCertificate(@PathParam("id") int id ){
		CertificateService personService = new CertificateService();
		return personService.getCertificate(id);
	}
}
