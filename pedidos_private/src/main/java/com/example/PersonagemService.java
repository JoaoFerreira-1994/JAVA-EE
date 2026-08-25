package com.example;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PersonagemService {

    @Inject
    private PersonagemRepository repository;


    public List<Personagem> listar() {
        return repository.listar();
    }


    @Transactional
    public Personagem criar(Personagem personagem) {

        if (personagem.getComidaFavorita() == null ||
            personagem.getComidaFavorita().isBlank()) {

            personagem.setComidaFavorita("Pizza");
        }

        repository.adicionar(personagem);

        return personagem;
    }

    public Personagem procurar(int id) {
    return repository.procurar(id);
    }

    @Transactional
    public boolean eliminar(int id) {

        Personagem personagem = repository.procurar(id);

        if (personagem == null) {
            return false;
        }

        repository.eliminar(id);
        return true;
    }


    @Transactional
    public Personagem atualizar(Personagem personagem) {
        return repository.atualizar(personagem);
    }


    @Transactional
    public Personagem atualizarParcial(
            int id,
            String nome,
            String especie,
            String comida) {

        return repository.update(id, nome, especie, comida);
    
    }
}