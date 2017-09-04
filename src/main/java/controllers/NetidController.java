package controllers;

import javax.ws.rs.*;
import javax.servlet.http.HttpSession;
import io.dropwizard.jersey.sessions.Session;

@Path("")
public class NetidController {

    @GET
    @Path("/netid")
    public String getNetid(@Session HttpSession session) {
        return "bal246";
    }
}