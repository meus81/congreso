package hemoterapia.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.linking.InjectLink;

import hemoterapia.domain.Certificate;
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
//	public Response savePerson(InputStream incomingData){

//		Enumeration<String> parametros = request.getAttributeNames();
//		while(parametros.hasMoreElements()) {
//			System.out.println(parametros.nextElement());
//		}
		
		System.out.println("calling the post a person... service");
        System.out.println("First Name = "+ person.getName());
        System.out.println("Last Name  = "+ person.getSurname());
        System.out.println("companions = " + person.getCompanions());
        System.out.println("certificate = " + person.getTitle());
        
        PersonService personService = new PersonService();
        personService.save(person);
        
        return "ok";

//		StringBuilder crunchifyBuilder = new StringBuilder();
//		try {
//			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
//			String line = null;
//			while ((line = in.readLine()) != null) {
//				crunchifyBuilder.append(line);
//			}
//		} catch (Exception e) {
//			System.out.println("Error Parsing: - ");
//		}
//		System.out.println("Data Received: " + crunchifyBuilder.toString());
//		
//        return Response.status(200).entity(crunchifyBuilder.toString()).build();
	}
	
}
