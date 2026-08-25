package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/personagem")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonagemController {

    @Inject
    private PersonagemService service;


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

    @GET
    @Path("/{id}")
    public Response procurar(@PathParam("id") int id) {

        Personagem personagem = service.procurar(id);

        if (personagem == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(personagem).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") int id) {

        boolean eliminado = service.eliminar(id);

        if (!eliminado) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response
                .noContent()
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(
            @PathParam("id") int id,
            Personagem personagem) {

        Personagem existente = service.procurar(id);

        if (existente == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        existente.setNome(personagem.getNome());
        existente.setEspecie(personagem.getEspecie());
        existente.setComidaFavorita(personagem.getComidaFavorita());

        Personagem atualizado = service.atualizar(existente);

        return Response.ok(atualizado).build();
    }

    @PATCH
    @Path("/{id}")
    public Response atualizarParcial(
            @PathParam("id") int id,
            Personagem personagem) {

        Personagem atualizado = service.atualizarParcial(
                id,
                personagem.getNome(),
                personagem.getEspecie(),
                personagem.getComidaFavorita()
        );

        if (atualizado == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(atualizado).build();
    }
}