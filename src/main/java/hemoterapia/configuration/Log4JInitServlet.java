package hemoterapia.configuration;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

public class Log4JInitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("Log4JInitServlet is initializing log4j");
		String log4jLocation = config.getInitParameter("log4j-properties-name");
		System.out.println("El properties file se enceuntra en: " + log4jLocation);

		// config.getInitParameter("log4j-properties-name");

		ServletContext sc = config.getServletContext();

		String webAppPath = sc.getRealPath("/");
		String log4jProp = webAppPath + log4jLocation;
		File log4jFileProp = new File(log4jProp);
		if (log4jFileProp.exists()) {
			System.out.println("Initializing log4j with: " + log4jProp);
			PropertyConfigurator.configure(log4jProp);
		} else {
			System.err.println("*** " + log4jProp + " file not found, so initializing log4j with BasicConfigurator");
			BasicConfigurator.configure();
		}
	}
}
