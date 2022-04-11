package br.edu.femass.projetobiblioteca.gui;

import br.edu.femass.projetobiblioteca.dao.AutorDao;
import br.edu.femass.projetobiblioteca.model.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AutorController implements Initializable {

    private AutorDao autorDao = new AutorDao();

    @FXML
    private ListView<Autor> LstAutores;

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
    private TextField TxtNome;

    @FXML
    private TextField TxtSobrenome;

    private void limparTela() {
        TxtNome.setText("");
        TxtSobrenome.setText("");
    }

    private void habilitarInterface(Boolean incluir){
        TxtNome.setDisable(!incluir);
        TxtSobrenome.setDisable(!incluir);
        BtnAceitar.setDisable(!incluir);
        BtnCancelar.setDisable(!incluir);
        BtnAdicionar.setDisable(incluir);
        BtnDeletar.setDisable(incluir);
        BtnAtualizar.setDisable(incluir);
        LstAutores.setDisable(incluir);
    }

    private void atualizarLista(){
        List<Autor> autores = new ArrayList<>();
        try {
            autores = autorDao.listar();
        } catch(NullPointerException e){
            System.out.println("Arquivo não encontrado.");
        } catch (Exception e) {
            System.out.println("Lista de Autores vazia!");
        }
        ObservableList<Autor> autoresOb = FXCollections.observableArrayList(autores);
        LstAutores.setItems(autoresOb);
    }

    private void exibirAutor(){
        Autor autor = LstAutores.getSelectionModel().getSelectedItem();
        if(autor==null) return;
        TxtNome.setText(autor.getNome());
        TxtSobrenome.setText(autor.getSobrenome());
    }

    @FXML
    private void LstAutores_Click(MouseEvent evento) {
        exibirAutor();
    }

    @FXML
    private void LstAutores_KeyPressed(KeyEvent evento) {
        exibirAutor();
    }

    @FXML
    private void BtnAdicionar_Click(ActionEvent evento){
        habilitarInterface(true);
        limparTela();
        TxtNome.requestFocus();
    }

    @FXML
    private void BtnDeletar_Click(ActionEvent evento){
        Autor autor = LstAutores.getSelectionModel().getSelectedItem();
        if(autor==null) return;

        try{
            autorDao.excluir(autor);
        }catch(Exception e){
            e.printStackTrace();
        }

        atualizarLista();
    }

    @FXML
    private void BtnAtualizar_Click(ActionEvent evento) throws Exception {

    }

    @FXML
    private void BtnAceitar_Click(ActionEvent evento){
        Autor autor = new Autor(TxtNome.getText(), TxtSobrenome.getText());

        try {
            autorDao.gravar(autor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizarLista();
        habilitarInterface(false);
    }

    @FXML
    private void BtnCancelar_Click(ActionEvent evento){
        habilitarInterface(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        atualizarLista();
    }
}