package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String especie;
    private String comidaFavorita;


    public Personagem() {
    }


    public Personagem(String nome, String especie, String comidaFavorita) {
        this.nome = nome;
        this.especie = especie;
        this.comidaFavorita = comidaFavorita;
    }


    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }


    public String getComidaFavorita() {
        return comidaFavorita;
    }

    public void setComidaFavorita(String comidaFavorita) {
        this.comidaFavorita = comidaFavorita;
    }
}