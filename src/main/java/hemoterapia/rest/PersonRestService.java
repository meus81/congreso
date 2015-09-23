package hemoterapia.rest;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import hemoterapia.domain.Person;
import hemoterapia.services.PersonService;

@ApplicationPath("/resources")
@Path("/")
public class PersonRestService {
	
	@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService(InputStream incomingData) {
		String result = "CongresoHemoterapiaRESTService Successfully started..";
		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/default_person")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getDefaultPersonInJson(){
		PersonService personService = new PersonService();
		return personService.getDefaultPerson();		
	}
	
	@GET
	@Path("/person")
	@Produces(MediaType.APPLICATION_JSON)
	public GenericEntity<List<Person>> getAlltPersonInJson(){
		PersonService personService = new PersonService();
		return new GenericEntity<List<Person>>(personService.getAllPersons()){
			
		};	
	}
	
	@POST
	@Path("/person")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String savePerson(Person person){
        System.out.println("First Name = "+ person.getName());
        System.out.println("Last Name  = "+ person.getSurname());
        
        return "ok";
	}
	
}
