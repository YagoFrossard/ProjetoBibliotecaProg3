package br.edu.femass.projetobiblioteca.model;

public class Aluno extends Usuario{

    public Aluno(){}

    public Aluno(String nome) {
        super(nome);
        this.prazoDev = 5;
    }

    @Override
    public String toString() {
        return id + ": Aluno " + nome;
    }
}
