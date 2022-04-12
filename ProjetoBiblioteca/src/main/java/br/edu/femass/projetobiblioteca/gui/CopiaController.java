package br.edu.femass.projetobiblioteca.gui;

import br.edu.femass.projetobiblioteca.dao.CopiaDao;
import br.edu.femass.projetobiblioteca.dao.LivroDao;
import br.edu.femass.projetobiblioteca.model.Autor;
import br.edu.femass.projetobiblioteca.model.Copia;
import br.edu.femass.projetobiblioteca.model.Genero;
import br.edu.femass.projetobiblioteca.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CopiaController implements Initializable {

    //TODO: Finalizar controller de Cópia
    private CopiaDao copiaDao = new CopiaDao();
    private LivroDao livroDao = new LivroDao();

    @FXML
    private ListView<Copia> LstCopias;

    @FXML
    private Button BtnAdicionar;

    @FXML
    private Button BtnDeletar;

    @FXML
    private Button BtnAtualizar;

    @FXML
    private Button BtnAceitar;

    @FXML
    private Button BtnCancelar;

    @FXML
    private TextField TxtCodigo;

    @FXML
    private ComboBox<Livro> CboLivro;

    @FXML
    private CheckBox ChkFixo;

    private void limparTela() {
        TxtCodigo.setText("");
        CboLivro.setValue(null);
        ChkFixo.setSelected(false);
    }

    private void habilitarInterface(Boolean incluir){
        ChkFixo.setDisable(!incluir);
        BtnAceitar.setDisable(!incluir);
        BtnCancelar.setDisable(!incluir);
        BtnAdicionar.setDisable(incluir);
        BtnDeletar.setDisable(incluir);
        BtnAtualizar.setDisable(incluir);
        LstCopias.setDisable(incluir);
        CboLivro.setDisable(!incluir);
    }

    private void atualizarLista(){
        List<Copia> copias;
        List<Livro> livros;
        try {
            livros = livroDao.listar();
            copias = copiaDao.listar();
        } catch (Exception e) {
            livros = new ArrayList<>();
            copias = new ArrayList<>();
        }
        ObservableList<Copia> copiasOb = FXCollections.observableArrayList(copias);
        LstCopias.setItems(copiasOb);

        ObservableList<Livro> livroOb = FXCollections.observableArrayList(livros);
        CboLivro.setItems(livroOb);
    }

    private void exibirCopia(){
        Copia copia = LstCopias.getSelectionModel().getSelectedItem();
        if(copia==null) return;
        TxtCodigo.setText(copia.getCodigo().toString());
        CboLivro.setValue(copia.getLivroOriginal());
        ChkFixo.setSelected(copia.getFixo());
    }

    @FXML
    private void LstCopias_Click(MouseEvent evento) {
        exibirCopia();
    }

    @FXML
    private void LstCopias_KeyPressed(KeyEvent evento) {
        exibirCopia();
    }

    @FXML
    private void BtnAdicionar_Click(ActionEvent evento){
        habilitarInterface(true);
        limparTela();
    }

    @FXML
    private void BtnDeletar_Click(ActionEvent evento){
        Copia copia = LstCopias.getSelectionModel().getSelectedItem();
        if(copia==null) return;
        if(copia.getFixo()){
            System.out.println("Não pode deletar um livro fixo!");
            return;
        }
        try{
            copiaDao.excluir(copia);
        }catch(Exception e){
            e.printStackTrace();
        }

        atualizarLista();
    }

    @FXML
    private void BtnAtualizar_Click(ActionEvent evento) throws Exception {
        atualizarLista();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        atualizarLista();
    }
}
