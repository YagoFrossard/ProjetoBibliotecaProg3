package br.edu.femass.projetobiblioteca.model;

import java.util.*;

public class Livro {

    private List<Genero> generos = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private Integer ano_lancamento;
    private Integer edicao;
    private String nome;

    public Livro(){}

    public Livro(Integer ano_lancamento, Integer edicao, String nome){
        this.ano_lancamento = ano_lancamento;
        this.edicao = edicao;
        this.nome = nome;
    }

    public Integer getAno_lancamento() {
        return ano_lancamento;
    }

    public void setAno_lancamento(Integer ano_lancamento) {
        this.ano_lancamento = ano_lancamento;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public Autor getPrimeiroAutor(){
        return autores.get(0);
    }

    public Genero getPrimeiroGenero(){
        return generos.get(0);
    }

    public void setPrimeiroAutor(Autor autor){
        autores.add(autor);
    }

    public void setPrimeiroGenero(Genero genero){
        generos.add(genero);
    }

    @Override
    public String toString(){
        return nome + "; " + edicao + "º edição, " + ano_lancamento;
    }
}
