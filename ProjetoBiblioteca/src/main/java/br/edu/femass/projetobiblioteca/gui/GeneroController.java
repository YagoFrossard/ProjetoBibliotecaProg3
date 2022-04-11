package br.edu.femass.projetobiblioteca.gui;

import br.edu.femass.projetobiblioteca.dao.GeneroDao;
import br.edu.femass.projetobiblioteca.model.Genero;
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

public class GeneroController implements Initializable{

    private GeneroDao generoDao = new GeneroDao();

    @FXML
    private ListView<Genero> LstGeneros;

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
    private TextField TxtGenero;

    @FXML
    private TextArea TxtDescricao;

    private void limparTela() {
        TxtGenero.setText("");
        TxtDescricao.setText("");
    }

    private void habilitarInterface(Boolean incluir){
        TxtGenero.setDisable(!incluir);
        TxtDescricao.setDisable(!incluir);
        BtnAceitar.setDisable(!incluir);
        BtnCancelar.setDisable(!incluir);
        BtnAdicionar.setDisable(incluir);
        BtnDeletar.setDisable(incluir);
        BtnAtualizar.setDisable(incluir);
        LstGeneros.setDisable(incluir);
    }

    private void atualizarLista(){
        List<Genero> generos;
        try {
            generos = generoDao.listar();
        } catch (Exception e) {
            generos = new ArrayList<>();
        }
        ObservableList<Genero> generosOb = FXCollections.observableArrayList(generos);
        LstGeneros.setItems(generosOb);
    }

    private void exibirGenero(){
        Genero genero = LstGeneros.getSelectionModel().getSelectedItem();
        if(genero==null) return;
        TxtGenero.setText(genero.getNome());
        TxtDescricao.setText(genero.getDescricao());
    }

    @FXML
    private void LstGeneros_Click(MouseEvent evento) {
        exibirGenero();
    }

    @FXML
    private void LstGeneros_KeyPressed(KeyEvent evento) {
        exibirGenero();
    }

    @FXML
    private void BtnAdicionar_Click(ActionEvent evento){
        habilitarInterface(true);
        limparTela();
        TxtGenero.requestFocus();
    }

    @FXML
    private void BtnDeletar_Click(ActionEvent evento){
        Genero genero = LstGeneros.getSelectionModel().getSelectedItem();
        if(genero==null) return;

        try{
            generoDao.excluir(genero);
        }catch(Exception e){
            e.printStackTrace();
        }

        atualizarLista();
    }

    @FXML
    private void BtnAtualizar_Click(ActionEvent evento) throws Exception {
        atualizarLista();
    }

    @FXML
    private void BtnAceitar_Click(ActionEvent evento){
        Genero genero = new Genero(TxtGenero.getText(), TxtDescricao.getText());

        try {
            generoDao.gravar(genero);
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
