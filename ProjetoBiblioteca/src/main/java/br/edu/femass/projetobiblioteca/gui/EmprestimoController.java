package br.edu.femass.projetobiblioteca.gui;

import br.edu.femass.projetobiblioteca.dao.CopiaDao;
import br.edu.femass.projetobiblioteca.dao.EmprestimoDao;
import br.edu.femass.projetobiblioteca.dao.UsuarioDao;
import br.edu.femass.projetobiblioteca.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmprestimoController implements Initializable {

    private UsuarioDao usuarioDao = new UsuarioDao();
    private CopiaDao copiaDao = new CopiaDao();
    private EmprestimoDao emprestimoDao = new EmprestimoDao();

    @FXML
    private ListView<Emprestimo> LstEmprestimos;

    @FXML
    private Button BtnEmprestar;

    @FXML
    private Button BtnFinalizar;

    @FXML
    private Button BtnAtualizar;

    @FXML
    private Button BtnAceitar;

    @FXML
    private Button BtnCancelar;

    @FXML
    private TextField TxtRetirada;

    @FXML
    private TextField TxtPrevEntrega;

    @FXML
    private TextField TxtEntrega;

    @FXML
    private ComboBox<Copia> CboCopia;

    @FXML
    private ComboBox<Usuario> CboUsuario;

    private void limparTela() {
        TxtEntrega.setText("");
        TxtPrevEntrega.setText("");
        TxtRetirada.setText("");
        CboUsuario.setValue(null);
        CboCopia.setValue(null);
    }

    private void habilitarInterface(Boolean incluir){
        BtnAceitar.setDisable(!incluir);
        BtnCancelar.setDisable(!incluir);
        BtnEmprestar.setDisable(incluir);
        BtnFinalizar.setDisable(incluir);
        BtnAtualizar.setDisable(incluir);
        LstEmprestimos.setDisable(incluir);
        CboCopia.setDisable(!incluir);
        CboUsuario.setDisable(!incluir);
    }

    private void atualizarLista(){
        List<Emprestimo> emprestimos;
        List<Copia> copias = new ArrayList<>();
        List<Usuario> usuarios = new ArrayList<>();
        try {
            usuarios = usuarioDao.listar();
            copias = copiaDao.listar();
            emprestimos = emprestimoDao.listar();
        } catch (Exception e) {
            emprestimos = new ArrayList<>();
        }
        ObservableList<Copia> copiasOb = FXCollections.observableArrayList(copias);
        CboCopia.setItems(copiasOb);

        ObservableList<Usuario> usuariosOb = FXCollections.observableArrayList(usuarios);
        CboUsuario.setItems(usuariosOb);

        ObservableList<Emprestimo> emprestimosOb = FXCollections.observableArrayList(emprestimos);
        LstEmprestimos.setItems(emprestimosOb);
    }

    private void exibirEmprestimo(){
        Emprestimo emprestimo = LstEmprestimos.getSelectionModel().getSelectedItem();
        if(emprestimo==null) return;
        TxtRetirada.setText(emprestimo.getDataEmprestimo().toString());
        TxtPrevEntrega.setText(emprestimo.getDataPrevistaDevolucao().toString());
        TxtEntrega.setText(emprestimo.getDataEfetivaDevolucao().toString());
        CboUsuario.setValue(emprestimo.getUsuario());
        CboCopia.setValue(emprestimo.getCopia());
    }

    @FXML
    private void LstEmprestimos_Click(MouseEvent evento) {
        exibirEmprestimo();
    }

    @FXML
    private void LstEmprestimos_KeyPressed(KeyEvent evento) {
        exibirEmprestimo();
    }

    @FXML
    private void BtnEmprestar_Click(ActionEvent evento){
        habilitarInterface(true);
        limparTela();
    }

    @FXML
    private void BtnFinalizar_Click(ActionEvent evento){
        Emprestimo emprestimo = LstEmprestimos.getSelectionModel().getSelectedItem();
        if(emprestimo==null) return;

        try{
            emprestimo.setDataEfetivaDevolucao(LocalDate.now());
            emprestimo.getCopia().setIsAlugado(false);
            emprestimoDao.updateXML();
            usuarioDao.updateXML();
            copiaDao.updateXML();
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
        Emprestimo emprestimo = new Emprestimo(
                CboUsuario.getValue(),
                CboCopia.getValue()
        );
        if(emprestimo.getUsuario()==null || emprestimo.getCopia()==null){
            System.out.println("Algo deu errado!");
            habilitarInterface(false);
            return;
        }

        try {
            emprestimo.getUsuario().getEmprestimos().add(emprestimo);
            emprestimo.getCopia().setIsAlugado(true);
            usuarioDao.updateXML();
            copiaDao.updateXML();
            emprestimoDao.gravar(emprestimo);
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
