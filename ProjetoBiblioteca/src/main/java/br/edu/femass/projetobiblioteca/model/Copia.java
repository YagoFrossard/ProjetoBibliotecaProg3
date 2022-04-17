package br.edu.femass.projetobiblioteca.model;

import br.edu.femass.projetobiblioteca.dao.CopiaDao;

public class Copia {

    private Integer codigo;
    private Livro livroOriginal;
    private Boolean fixo = false;
    private Boolean isAlugado = false;
    private static Integer proxCodigo = pegarProximoCodigo();

    public Copia(){}

    public Copia(Livro livroOriginal, Boolean fixo){
        this.livroOriginal = livroOriginal;
        this.fixo = fixo;
        this.codigo = proxCodigo;
        proxCodigo++;
    }

    public static Integer pegarProximoCodigo(){
        CopiaDao dao = new CopiaDao();
        Integer proxCodigo = 0;
        try{
            proxCodigo = dao.listar().get(dao.listar().size() - 1).getCodigo() + 1;
        }catch (Exception e){
            proxCodigo = 0;
        }finally {
            return proxCodigo;
        }
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

    public Integer getCodigo() {
        return codigo;
    }

    public Boolean getAlugado() {
        return isAlugado;
    }

    public void setIsAlugado(Boolean b){
        this.isAlugado = b;
    }

    @Override
    public String toString(){
        String fixo = getFixo() ? "Fixo: " : "";
        return codigo + " - " + fixo + livroOriginal.getNome();
    }
}
