package br.edu.femass.projetobiblioteca.model;


import java.time.LocalDate;
import java.util.Date;

public class Emprestimo {

    private Usuario usuario;
    private Copia copia;
    private LocalDate dataEmprestimo = LocalDate.now();
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataEfetivaDevolucao;

    public Emprestimo(){}

    public Emprestimo(Usuario usuario, Copia copia){
        if(podeEmprestar(usuario)) {
            this.usuario = usuario;
            this.copia = copia;
            this.dataPrevistaDevolucao = dataEmprestimo.plusDays((long) usuario.getPrazoDev());
        }else{
            System.out.println("Esse usuário já possui 5 empréstimos ativos!");
            return;
        }
    }

    public Boolean podeEmprestar(Usuario usuario){
        return usuario.pegarEmprestimosAtivos() < 5;
    }

    public Boolean verificarDevolucao(){
        return (!(dataEfetivaDevolucao == null));
    }

    public Boolean verificarAtraso(){
        if(LocalDate.now().isAfter(dataPrevistaDevolucao)){
            return true;
        }else{
            return false;
        }
    }

    public void setDataEfetivaDevolucao(LocalDate dataEfetivaDevolucao) {
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }
}
