package com.example;

import java.io.IOException;
import jakarta.ws.rs.GET;

@Path("/test/{User]")
public class App{

  @GET
  public void ping(@QueryParam("id") int id, @PathParam("user") String user){
    System.out.println("Olá");

    System.out.println("Id igual a :" + id )
  }
}
