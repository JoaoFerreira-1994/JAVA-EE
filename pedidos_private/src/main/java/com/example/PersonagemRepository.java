package com.example;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class PersonagemRepository {

    @PersistenceContext
    private EntityManager em;


    // POST - adicionar personagem
    public void adicionar(Personagem personagem) {
        em.persist(personagem);
    }


    // GET - listar todos
    public List<Personagem> listar() {
        return em.createQuery(
            "SELECT p FROM Personagem p",
            Personagem.class
        ).getResultList();
    }


    // GET - procurar por ID
    public Personagem procurar(int id) {
        return em.find(Personagem.class, id);
    }


    // Verificar se existe
    public boolean existe(int id) {
        return em.find(Personagem.class, id) != null;
    }


    // Contar
    public long size() {
        return em.createQuery(
            "SELECT COUNT(p) FROM Personagem p",
            Long.class
        ).getSingleResult();
    }


    // DELETE
    public void eliminar(int id) {

        Personagem personagem = em.find(Personagem.class, id);

        if (personagem != null) {
            em.remove(personagem);
        }
    }


    // PUT
    public Personagem atualizar(Personagem personagem) {
        return em.merge(personagem);
    }


    // PATCH - atualizar apenas alguns campos
    public Personagem update(
        int id,
        String nome,
        String especie,
        String comida
    ) {

        Personagem personagem = em.find(Personagem.class, id);

        if (personagem == null) {
            return null;
        }

        if (nome != null && !nome.isEmpty()) {
            personagem.setNome(nome);
        }

        if (especie != null && !especie.isEmpty()) {
            personagem.setEspecie(especie);
        }

        if (comida != null && !comida.isEmpty()) {
            personagem.setComidaFavorita(comida);
        }

        return personagem;
    }
}