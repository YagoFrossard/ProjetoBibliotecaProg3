package br.edu.femass.projetobiblioteca.dao;

import br.edu.femass.projetobiblioteca.model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroDados {
    private List<Livro> livros = new ArrayList<>();

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
