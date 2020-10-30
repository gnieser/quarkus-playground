package org.acme;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.logging.Logger;

@Path("option")
@Consumes(MediaType.TEXT_HTML)
@Produces(MediaType.TEXT_HTML)
public class OptionResource {

    private static final Logger LOGGER = Logger.getLogger(OptionResource.class.getName());

    @Inject
    Template option;

    Option selection = Option.OPTION_B;

    @GET
    public TemplateInstance get() {
        return option.data("selection", selection);
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response post(@MultipartForm Form form) {
        selection = form.option;
        return Response.status(301)
                .location(URI.create("/option"))
                .build();
    }
}