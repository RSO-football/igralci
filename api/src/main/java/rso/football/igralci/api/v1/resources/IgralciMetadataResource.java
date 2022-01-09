package rso.football.igralci.api.v1.resources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import rso.football.igralci.lib.IgralciMetadata;
import rso.football.igralci.services.beans.IgralciMetadataBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@Log
@ApplicationScoped
@Path("/igralci")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, DELETE, PUT, HEAD, OPTIONS")
public class IgralciMetadataResource {

    private Logger log = Logger.getLogger(IgralciMetadataResource.class.getName());

    @Inject
    private IgralciMetadataBean igralciMetadataBean;

    @Context
    protected UriInfo uriInfo;

    @Operation(description = "Get all igralci metadata.", summary = "Get all metadata")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "List of igralci metadata",
                    content = @Content(schema = @Schema(implementation = IgralciMetadata.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )})
    @GET
    public Response getImageMetadata() {

        List<IgralciMetadata> igralciMetadata = igralciMetadataBean.getIgralciMetadataFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(igralciMetadata).build();
    }

    @Operation(description = "Get metadata for one igralec.", summary = "Get metadata for one igralec")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Igrisce metadata",
                    content = @Content(
                            schema = @Schema(implementation = IgralciMetadata.class))
            )})
    @GET
    @Path("/{igralciMetadataId}")
    public Response getImageMetadata(@Parameter(description = "Metadata ID.", required = true)
                                         @PathParam("igralciMetadataId") Integer igralciMetadataId) {

        IgralciMetadata igralciMetadata = igralciMetadataBean.getIgralciMetadata(igralciMetadataId);

        if (igralciMetadata == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(igralciMetadata).build();
    }

    @Operation(description = "Add igralec metadata.", summary = "Add metadata")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Metadata successfully added."
            ),
            @APIResponse(responseCode = "400", description = "Bad request.")
    })
    @POST
    public Response createIgralciMetadata(@RequestBody(
            description = "DTO object with igralci metadata.",
            required = true, content = @Content(
            schema = @Schema(implementation = IgralciMetadata.class))) IgralciMetadata igralciMetadata) {

        if ((igralciMetadata.getName() == null || igralciMetadata.getDescription() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            igralciMetadata = igralciMetadataBean.createIgralciMetadata(igralciMetadata);
        }

        return Response.status(Response.Status.CREATED).entity(igralciMetadata).build();

    }

    @Operation(description = "Update metadata for on igralec.", summary = "Update metadata")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Metadata successfully updated."
            ),
            @APIResponse(responseCode = "404", description = "Not found.")
    })
    @PUT
    @Path("{igralciMetadataId}")
    public Response putImageMetadata(@Parameter(description = "Metadata ID.", required = true) 
                                     @PathParam("igralciMetadataId") Integer igralciMetadataId,
                                     @RequestBody(
                                             description = "DTO object with igralec metadata.",
                                             required = true, content = @Content(
                                             schema = @Schema(implementation = IgralciMetadata.class)))
                                             IgralciMetadata igralciMetadata) {

        igralciMetadata = igralciMetadataBean.putIgralciMetadata(igralciMetadataId, igralciMetadata);

        if (igralciMetadata == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.NO_CONTENT).build();

    }

    @Operation(description = "Delete metadata for one igralec.", summary = "Delete metadata")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Metadata successfully deleted."
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Not found."
            )
    })
    @DELETE
    @Path("{igralciMetadataId}")
    public Response deleteIgralciMetadata(@Parameter(description = "Metadata ID.", required = true)
                                              @PathParam("igralciMetadataId") Integer igralciMetadataId) {

        boolean deleted = igralciMetadataBean.deleteIgralciMetadata(igralciMetadataId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}