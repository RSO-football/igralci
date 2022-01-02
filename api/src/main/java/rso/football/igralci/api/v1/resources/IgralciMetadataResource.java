package rso.football.igralci.api.v1.resources;

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

    @GET
    public Response getImageMetadata() {

        List<IgralciMetadata> igralciMetadata = igralciMetadataBean.getIgralciMetadataFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(igralciMetadata).build();
    }

    @GET
    @Path("/{igralciMetadataId}")
    public Response getImageMetadata(@PathParam("igralciMetadataId") Integer igralciMetadataId) {

        IgralciMetadata igralciMetadata = igralciMetadataBean.getIgralciMetadata(igralciMetadataId);

        if (igralciMetadata == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(igralciMetadata).build();
    }

    @POST
    public Response createIgralciMetadata(IgralciMetadata igralciMetadata) {

        if ((igralciMetadata.getName() == null || igralciMetadata.getDescription() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            igralciMetadata = igralciMetadataBean.createIgralciMetadata(igralciMetadata);
        }

        return Response.status(Response.Status.CONFLICT).entity(igralciMetadata).build();

    }

    @PUT
    @Path("{igralciMetadataId}")
    public Response putImageMetadata(@PathParam("igralciMetadataId") Integer igralciMetadataId,
                                     IgralciMetadata igralciMetadata) {

        igralciMetadata = igralciMetadataBean.putIgralciMetadata(igralciMetadataId, igralciMetadata);

        if (igralciMetadata == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.NOT_MODIFIED).build();

    }

    @DELETE
    @Path("{igralciMetadataId}")
    public Response deleteIgralciMetadata(@PathParam("igralciMetadataId") Integer igralciMetadataId) {

        boolean deleted = igralciMetadataBean.deleteIgralciMetadata(igralciMetadataId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}