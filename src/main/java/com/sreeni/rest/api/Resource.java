package com.sreeni.rest.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("home")
public class Resource {
    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        return "Hello, world!";
    }
    
    @GET
    @Path("param")
    @Produces(MediaType.TEXT_PLAIN)
    public String paramMethod(@QueryParam("name") String name) {
      return "Hello, " + name;
    }
    
    @GET
    @Path("path/{var}")
    @Produces(MediaType.TEXT_PLAIN)
    public String pathMethod(@PathParam("var") String name) {
        return "Hello, " + name;
    }
    
    @POST
    @Path("post")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String postMethod(@FormParam("name") String name) {
      return "<h2>Hello, " + name + "</h2>";
    }
    
    @GET
    @Path("/person/{name}")
    @Produces(MediaType.TEXT_HTML)
    public String getHTMLData(@PathParam("name") String name) {
        return name;
    }   
    
    @GET
    @Path("/person/xml/{name}")
    @Produces(MediaType.APPLICATION_XML)
    public Person getXMLData( @PathParam("name") String name ) {
        
        if (name.equalsIgnoreCase("Java")) {
            return new Person("Java Honk","NY");
        } else {
            return new Person("Some Other Person","NY");
        }
    }
    
    @GET
    @Path("/person/json/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getJASONData (@PathParam("name") String name ) {
        
        if (name.equalsIgnoreCase("Java")) {
            return new Person("Java Honk","NY");
        } else {
            return new Person("Some Other Person","NY");
        }
    }   
    
    @POST
    @Path("/postdata/applicationOctetStream")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response postDataStream(@Context HttpServletRequest request,
            @Context HttpServletResponse response) {
        
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
 
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
                    
        }
        
        body = stringBuilder.toString();
        return Response.status(200).entity(body).build();   
    }
}
