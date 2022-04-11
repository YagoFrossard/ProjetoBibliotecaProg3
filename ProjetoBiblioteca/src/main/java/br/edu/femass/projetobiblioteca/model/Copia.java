package br.edu.femass.projetobiblioteca.model;

public class Copia {

    private Integer codigo;
    private Livro livroOriginal;
    private Boolean fixo;
    private static Integer proxCodigo = 0;

    public Copia(){}

    public Copia(Livro livroOriginal, Boolean fixo){
        this.livroOriginal = livroOriginal;
        this.fixo = fixo;
        this.codigo = proxCodigo;
        proxCodigo++;
    }

    public Livro getLivroOriginal() {
        return livroOriginal;
    }

    public void setLivroOriginal(Livro livroOriginal) {
        this.livroOriginal = livroOriginal;
    }

    public Boolean getFixo() {
        return fixo;
    }

    public void setFixo(Boolean fixo) {
        this.fixo = fixo;
    }
}
