package br.edu.femass.projetobiblioteca.dao;

import java.util.List;

public interface Dao<T> {
    //Interface utilizada para controlar os processos de CRUD das classes
    public void gravar(T objeto);
    public List<T> listar();
    public void excluir(T objeto);
}
