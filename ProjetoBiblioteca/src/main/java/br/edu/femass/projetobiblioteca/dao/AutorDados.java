package br.edu.femass.projetobiblioteca.dao;

import br.edu.femass.projetobiblioteca.model.Autor;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;

public class AutorDados {
    private List<Autor> autores = new ArrayList<>();

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
}
