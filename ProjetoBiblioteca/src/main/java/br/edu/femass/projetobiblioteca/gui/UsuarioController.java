package br.edu.femass.projetobiblioteca.gui;

import br.edu.femass.projetobiblioteca.dao.GeneroDao;
import br.edu.femass.projetobiblioteca.dao.UsuarioDao;
import br.edu.femass.projetobiblioteca.model.Aluno;
import br.edu.femass.projetobiblioteca.model.Genero;
import br.edu.femass.projetobiblioteca.model.Professor;
import br.edu.femass.projetobiblioteca.model.Usuario;
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

public class UsuarioController implements Initializable {

    private UsuarioDao usuarioDao = new UsuarioDao();

    @FXML
    private ListView<Usuario> LstUsuarios;

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
    private TextField TxtId;

    @FXML
    private RadioButton RdbAluno;

    @FXML
    private RadioButton RdbProfessor;

    @FXML
    private ToggleGroup TgUsuario;

    private void limparTela() {
        TxtId.setText("");
        TxtNome.setText("");
        RdbAluno.setSelected(true);
    }

    private void habilitarInterface(Boolean incluir){
        TxtNome.setDisable(!incluir);
        RdbAluno.setDisable(!incluir);
        RdbProfessor.setDisable(!incluir);
        BtnAceitar.setDisable(!incluir);
        BtnCancelar.setDisable(!incluir);
        BtnAdicionar.setDisable(incluir);
        BtnDeletar.setDisable(incluir);
        BtnAtualizar.setDisable(incluir);
        LstUsuarios.setDisable(incluir);
    }

    private void atualizarLista(){
        List<Usuario> usuarios;
        try {
            usuarios = usuarioDao.listar();
        } catch (Exception e) {
            usuarios = new ArrayList<>();
        }
        ObservableList<Usuario> usuariosOb = FXCollections.observableArrayList(usuarios);
        LstUsuarios.setItems(usuariosOb);
    }

    private void exibirUsuario(){
        Usuario usuario = LstUsuarios.getSelectionModel().getSelectedItem();
        if(usuario==null) return;
        TxtNome.setText(usuario.getNome());
        TxtId.setText(usuario.getId().toString());
    }

    @FXML
    private void LstUsuarios_Click(MouseEvent evento) {
        exibirUsuario();
    }

    @FXML
    private void LstUsuarios_KeyPressed(KeyEvent evento) {
        exibirUsuario();
    }

    @FXML
    private void BtnAdicionar_Click(ActionEvent evento){
        habilitarInterface(true);
        limparTela();
        TxtNome.requestFocus();
    }

    @FXML
    private void BtnDeletar_Click(ActionEvent evento){
        Usuario usuario = LstUsuarios.getSelectionModel().getSelectedItem();
        if(usuario==null) return;

        try{
            usuarioDao.excluir(usuario);
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
        Usuario usuario;
        if(RdbAluno.isSelected()){
            usuario = new Aluno(TxtNome.getText());
        }else{
            usuario = new Professor(TxtNome.getText());
        }

        try {
            usuarioDao.gravar(usuario);
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
