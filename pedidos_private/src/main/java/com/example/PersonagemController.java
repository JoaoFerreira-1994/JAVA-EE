package com.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/personagem")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonagemController {

    private PersonagemService service = new PersonagemService();

    @GET
    public Response listar() {
        return Response.ok(service.listar()).build();
    }

    @POST
    public Response criar(Personagem personagem) {

        Personagem novaPersonagem = service.criar(personagem);

        return Response
                .status(Response.Status.CREATED)
                .entity(novaPersonagem)
                .build();
    }
}