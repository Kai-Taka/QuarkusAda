package br.com.bb;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/hello")
public class GreetingResource {
    private static final Logger log = LoggerFactory.getLogger(GreetingResource.class);
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        log.info("Hello test");
        return "Hello RESTEasy";
    }
}