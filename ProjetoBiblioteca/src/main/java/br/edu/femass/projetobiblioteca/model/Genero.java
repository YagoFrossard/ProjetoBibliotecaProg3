package br.edu.femass.projetobiblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class Genero {

    private String nome;
    private String descricao;
    public static List<Genero> todosGeneros = new ArrayList<>();

    public Genero(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
        todosGeneros.add(this);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return nome;
    }
}
