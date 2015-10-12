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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.linking.InjectLink;
import org.json.JSONObject;

import hemoterapia.domain.Certificate;
import hemoterapia.domain.Person;
import hemoterapia.services.CertificateService;
import hemoterapia.services.PersonService;

@ApplicationPath("/resources")
@Path("/")
public class PersonRestService {
	
	@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService() {
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
	@Path("/all_persons")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAlltPersonInJson(){
		PersonService personService = new PersonService();
		GenericEntity<List<Person>> list = new GenericEntity<List<Person>>(personService.getAllPersons()){
		};
		return Response.ok(list).build();
	}
	
	@GET
	@Path("/person/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPerson(@PathParam("id") int id ){
		PersonService personService = new PersonService();
		return personService.getPerson(id);
	}
	
	@POST
	@Path("/person")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response savePerson(InputStream incomingData){
//	public String savePerson(Person person){

//		System.out.println("calling the post a person... service");
//        System.out.println("First Name = "+ person.getName());
//        System.out.println("Last Name  = "+ person.getSurname());
//        System.out.println("companions = " + person.getCompanions());
//        System.out.println("certificate = " + person.getCertificate());
//        
//        PersonService personService = new PersonService();
//        personService.save(person);
//        
//        return "ok";

//		Enumeration<String> parametros = request.getAttributeNames();
//		while(parametros.hasMoreElements()) {
//			System.out.println(parametros.nextElement());
//		}
		
		StringBuilder personBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				personBuilder.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		System.out.println("Data Received: " + personBuilder.toString());
		
		JSONObject obj = new JSONObject(personBuilder.toString());
		String name = obj.getString("name");
		String surname = obj.getString("surname");
		int companions = obj.getInt("companions");
		int idCertificate = obj.getJSONObject("certificate").getInt("idCertificate");
		
		System.out.println("Data re-contruct " + name + "-" + surname + "-" + companions +"-" + idCertificate);
		
		CertificateService certificateService = new CertificateService();
		Certificate certificate  = certificateService.getCertificate(idCertificate);
		
		Person p = new Person();
		p.setName(name);
		p.setSurname(surname);
		p.setCompanions(companions);
		p.setCertificate(certificate);
		
		PersonService personService = new PersonService();
		personService.save(p);
		
        return Response.status(200).entity("Saved successful").build();
	}
	
}
