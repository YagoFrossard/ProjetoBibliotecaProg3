package br.edu.femass.projetobiblioteca.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EmprestimoTest {

    //Mock-objects
    Livro livro = new Livro(1990, 5, "O Dragão de Botas");
    Copia copia = new Copia(livro, false);
    Usuario aluno = new Aluno("Frederico");
    Usuario professor = new Professor("Douglas");
    Emprestimo emprestimo = new Emprestimo(aluno, copia);

    @Test
    void testeNaoEntregue(){
        Assertions.assertFalse(emprestimo.verificarDevolucao());
    }

    @Test
    void testeEntregue(){
        emprestimo.setDataEfetivaDevolucao(LocalDate.now().plusDays(2));
        Assertions.assertTrue(emprestimo.verificarDevolucao());
    }

    @Test
    void testeAtraso(){
        emprestimo.setDataPrevistaDevolucao(LocalDate.now().minusDays(10));
        Assertions.assertTrue(emprestimo.verificarAtraso());
    }

    @Test
    void testeNaoAtraso(){
        emprestimo.setDataPrevistaDevolucao(LocalDate.now().plusDays(2));
        Assertions.assertFalse(emprestimo.verificarAtraso());
    }

    @Test
    void testPermissaoEmprestar(){
        Assertions.assertTrue(emprestimo.podeEmprestar(professor));
    }

    @Test
    void testPermissaoNaoEmprestar(){
        for(int i = 0; i < 5; i++){
            aluno.emprestimos.add(new Emprestimo());
        }
        Assertions.assertFalse(emprestimo.podeEmprestar(aluno));
    }
}
