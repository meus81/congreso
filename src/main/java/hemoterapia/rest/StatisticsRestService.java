package hemoterapia.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hemoterapia.services.StatisticsService;
import hemoterapia.statistics.Statistics;

@ApplicationPath("/resources")
@Path("/")
public class StatisticsRestService {

	@GET
	@Path("/statistics")
	@Produces(MediaType.APPLICATION_JSON)
	public Statistics getStatics(){
		StatisticsService statisticsService = new StatisticsService();
		return statisticsService.getMainStatitics();
	}
}
