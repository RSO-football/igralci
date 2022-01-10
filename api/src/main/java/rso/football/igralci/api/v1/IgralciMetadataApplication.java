package rso.football.igralci.api.v1;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//some change

//TODO: change url to not localhost
@OpenAPIDefinition(info = @Info(title = "Igralci API", version = "v1",
        contact = @Contact(email = "rb2600@student.uni-lj.si"),
        license = @License(name = "dev"), description = "API for managing igralci metadata."),
        servers = @Server(url = "https://40.71.236.184:8081/"))
@ApplicationPath("/v1")
public class IgralciMetadataApplication extends Application {

}
