package br.edu.femass.projetobiblioteca.dao;

import br.edu.femass.projetobiblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDados {
    private List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
