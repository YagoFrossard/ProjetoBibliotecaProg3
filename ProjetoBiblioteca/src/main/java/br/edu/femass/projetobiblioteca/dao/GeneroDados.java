package br.edu.femass.projetobiblioteca.dao;

import br.edu.femass.projetobiblioteca.model.Genero;

import java.util.ArrayList;
import java.util.List;

public class GeneroDados {
    private List<Genero> generos = new ArrayList<>();

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }
}
