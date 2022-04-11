package br.edu.femass.projetobiblioteca.model;

public class Professor extends Usuario{

    public Professor(){}

    public Professor(String nome) {
        super(nome);
        this.prazoDev = 30;
    }

}
