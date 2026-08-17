package com.example;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
   
public class PersonagemRepository {

private final List<Personagem> personagens = new ArrayList<>();

public List<Personagem> listar() {
    return personagens;
}

public void adicionar(Personagem personagem) {
    personagens.add(personagem);
}
}