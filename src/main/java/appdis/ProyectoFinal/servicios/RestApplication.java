
package appdis.ProyectoFinal.servicios;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;


/**
 * 
 *
 * @author Geovanny Duchitanga, Diego Rodriguez, Italo Mendieta
 *
 */





@OpenAPIDefinition(servers = { @Server(description = "Servidor local", url = "/ProyectoFinal") })
@ApplicationPath("/rs")
public class RestApplication extends Application{

	
	
}
