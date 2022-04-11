package br.edu.femass.projetobiblioteca.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsuarioTest {

    //Mock-objects
    Usuario usuario = new Usuario();
    Emprestimo emp1 = new Emprestimo();
    Emprestimo emp2 = new Emprestimo();

    @Test
    void testeEmprestimosAtivos(){
        usuario.emprestimos.add(emp1);
        usuario.emprestimos.add(emp2);
        Boolean aff = (2 == usuario.pegarEmprestimosAtivos());
        Assertions.assertTrue(aff);
    }
}
