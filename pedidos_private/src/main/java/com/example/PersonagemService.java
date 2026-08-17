package com.example;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PersonagemService {

    @Inject
    private PersonagemRepository repository = new PersonagemRepository();

    public List<Personagem> listar() {
        return repository.listar();
    }

    public Personagem criar(Personagem personagem) {

        if (personagem.getComidaFavorita() == null ||
            personagem.getComidaFavorita().isBlank()) {

            personagem.setComidaFavorita("Pizza");
        }

        repository.adicionar(personagem);

        return personagem;
    }
}