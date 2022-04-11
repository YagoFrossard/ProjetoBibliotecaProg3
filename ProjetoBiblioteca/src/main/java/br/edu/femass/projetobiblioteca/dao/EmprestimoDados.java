package br.edu.femass.projetobiblioteca.dao;

import br.edu.femass.projetobiblioteca.model.Emprestimo;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoDados {
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
}
