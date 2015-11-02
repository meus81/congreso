package hemoterapia.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import hemoterapia.configuration.OSValidator;
import hemoterapia.domain.Certificate;
import hemoterapia.domain.LodgingsType;
import hemoterapia.domain.Person;
import hemoterapia.services.CertificateService;
import hemoterapia.services.LodgingsService;
import hemoterapia.services.PersonService;

@ApplicationPath("/resources")
@Path("/")
public class PersonRestService {
	private Logger log = Logger.getLogger("fileAppender");
	
	
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
	public Response getAlltPersonInJson(@QueryParam("name") String name, @QueryParam("surname") String surname){
		PersonService personService = new PersonService();
		GenericEntity<List<Person>> list = new GenericEntity<List<Person>>(personService.getPersons(name, surname)){
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

	
	@DELETE
	@Path("/person/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deletePerson(@PathParam("id") int id ){
		PersonService personService = new PersonService();
		personService.deletePerson(id);
		String result = "Person deleted";
		return Response.status(200).entity(result).build();
	}
	
	@POST
	@Path("/person")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject savePerson(@Context ServletContext context, InputStream incomingData){
//	public String savePerson(Person person){
//
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
		
		System.out.println("QUE SOS VOS log???? : " + log);

		log.debug("esta es una prueba para ver si se escribe en el log");
		log.debug("El puto contexto url que es???::: " + context.getContextPath());
		System.out.println("===================================================");
		System.out.println("El context path essss::::::::::");
		System.out.println(context.getContextPath());
		System.out.println("===================================================");
		
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
		String email = obj.getString("email");
		String address = obj.getString("address");
		int companions = obj.getInt("companions");
		int idCertificate = obj.getJSONObject("certificate").getInt("idCertificate");
		String lodgingsType = obj.getJSONObject("lodgings").getString("lodgings_type");
		
		System.out.println("Data re-contruct " + name + "-" + surname + "-" + companions +"-" + idCertificate);
		
		CertificateService certificateService = new CertificateService();
		Certificate certificate  = certificateService.getCertificate(idCertificate);
		
		LodgingsService lodgingsService = new LodgingsService();
		LodgingsType lodgingType = lodgingsService.getLodgingsType(lodgingsType); 
		
		Person p = new Person();
		p.setName(name);
		p.setSurname(surname);
		p.setAddress(address);
		p.setEmail(email);
		p.setCompanions(companions);
		p.setCertificate(certificate);
		p.setLodgings(lodgingType);
		
		PersonService personService = new PersonService();
		InputStream headerImage = context.getResourceAsStream("./img/nuevoLogo.png");
		
		String pathToSave= null;
		if (OSValidator.isWindows()){
			pathToSave = "C:" + File.separator + "TICKETS" + File.separator;
		} else if (OSValidator.isUnix()){
			pathToSave = "/home/meus/proyecto-personal/congreso/" + File.separator + "tickets" + File.separator;
		}
		System.out.println("VOY A GUARDAR EL PDF EN: " + pathToSave);
		String amountToPaid = personService.save(p, headerImage, pathToSave);

		JsonObject value = Json.createObjectBuilder()
	     .add("result", "ok")
	     .add("amount", amountToPaid)     
	     .build();
		
        return value;
	}
	
//	{
//	    "name": "marcos",
//	    "surname": "urbaneja",
//	    "email": "asdfdafs@yahoo.com",
//	    "address": "asdfasdf",
//	    "certificate": {
//	        "idCertificate": 1
//	    },
//	    "lodgings": {
//	        "lodgings_type": "hemoterapia.domain.WithLodgings"
//	    },
//	    "companions": 0
//	}
//	
//	{
//		result: {
//		valueType: "STRING"
//		chars: "ok"
//		string: "ok"
//		}-
//		amount: {
//		valueType: "STRING"
//		chars: "{amount: 1900.0}"
//		string: "{amount: 1900.0}"
//		}-
//		}
	
}
