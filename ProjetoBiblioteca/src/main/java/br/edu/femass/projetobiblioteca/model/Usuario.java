package br.edu.femass.projetobiblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    protected Integer id;
    protected String nome;
    protected Integer prazoDev;
    protected List<Emprestimo> emprestimos = new ArrayList<>();
    private static Integer proxId = 0;

    public Usuario(){}

    public Usuario(String nome){
        this.nome = nome;
        this.id = proxId;
        proxId++;
    }

    public int pegarEmprestimosAtivos(){
        int emprestimosAtivos = 0;
        for(Emprestimo emprestimo : emprestimos){
            if(!emprestimo.verificarDevolucao()) emprestimosAtivos++;
        }
        return emprestimosAtivos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPrazoDev() {
        return prazoDev;
    }

    public void setPrazoDev(Integer prazoDev) {
        this.prazoDev = prazoDev;
    }
}
