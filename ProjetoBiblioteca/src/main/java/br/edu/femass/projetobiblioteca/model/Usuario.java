package br.edu.femass.projetobiblioteca.model;

import br.edu.femass.projetobiblioteca.dao.UsuarioDao;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    protected Integer id;
    protected String nome;
    protected Integer prazoDev;
    protected List<Emprestimo> emprestimos = new ArrayList<>();
    protected static Integer proxId = pegarProximoId();

    public Usuario(){}

    public Usuario(String nome){
        this.nome = nome;
        this.id = proxId;
        proxId++;
    }

    public static Integer pegarProximoId(){
        UsuarioDao dao = new UsuarioDao();
        Integer proxId = 0;
        try{
            proxId = dao.listar().get(dao.listar().size() - 1).getId() + 1;
        }catch (Exception e){
            proxId = 0;
        }finally {
            return proxId;
        }
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

    public Integer getId(){ return this.id; }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}
