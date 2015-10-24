package hemoterapia.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hemoterapia.domain.LodgingsType;
import hemoterapia.services.LodgingsService;

@ApplicationPath("/resources")
@Path("/")
public class LodgingsRestService {
	@GET
	@Path("/lodgings/{subClassName}")
	@Produces(MediaType.APPLICATION_JSON)
	public LodgingsType getLodgings(@PathParam("subClassName") String subClassName) {
		System.out.println("El nombre de la subclase es:  " + subClassName);
		LodgingsService lodgingsService = new LodgingsService();
		return lodgingsService.getLodgingsType(subClassName);
	}
}
