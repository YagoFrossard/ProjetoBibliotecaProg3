package br.edu.femass.projetobiblioteca.gui;

import br.edu.femass.projetobiblioteca.dao.AutorDao;
import br.edu.femass.projetobiblioteca.dao.GeneroDao;
import br.edu.femass.projetobiblioteca.dao.LivroDao;
import br.edu.femass.projetobiblioteca.model.Autor;
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

public class LivroController implements Initializable {
    //TODO: colocar mais de um autor/genero nos livros
    private LivroDao livroDao = new LivroDao();
    private AutorDao autorDao = new AutorDao();
    private GeneroDao generoDao = new GeneroDao();

    @FXML
    private ListView<Livro> LstLivros;

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
    private TextField TxtAnoLanc;

    @FXML
    private TextField TxtEdicao;

    @FXML
    private ComboBox<Autor> CboAutor;

    @FXML
    private ComboBox<Genero> CboGenero;

    private void limparTela() {
        TxtNome.setText("");
        TxtAnoLanc.setText("");
        TxtEdicao.setText("");
        CboAutor.setValue(null);
        CboGenero.setValue(null);
    }

    private void habilitarInterface(Boolean incluir){
        TxtNome.setDisable(!incluir);
        TxtAnoLanc.setDisable(!incluir);
        TxtEdicao.setDisable(!incluir);
        BtnAceitar.setDisable(!incluir);
        BtnCancelar.setDisable(!incluir);
        BtnAdicionar.setDisable(incluir);
        BtnDeletar.setDisable(incluir);
        BtnAtualizar.setDisable(incluir);
        LstLivros.setDisable(incluir);
        CboGenero.setDisable(!incluir);
        CboAutor.setDisable(!incluir);
    }

    private void atualizarLista(){
        List<Livro> livros;
        List<Autor> autores = new ArrayList<>();
        List<Genero> generos = new ArrayList<>();
        try {
            autores = autorDao.listar();
            generos = generoDao.listar();
            livros = livroDao.listar();
        } catch (Exception e) {
            livros = new ArrayList<>();
        }
        ObservableList<Livro> livrosOb = FXCollections.observableArrayList(livros);
        LstLivros.setItems(livrosOb);

        ObservableList<Autor> autoresOb = FXCollections.observableArrayList(autores);
        CboAutor.setItems(autoresOb);

        ObservableList<Genero> generosOb = FXCollections.observableArrayList(generos);
        CboGenero.setItems(generosOb);
    }

    private void exibirLivro(){
        Livro livro = LstLivros.getSelectionModel().getSelectedItem();
        if(livro==null) return;
        TxtNome.setText(livro.getNome());
        TxtAnoLanc.setText(livro.getAno_lancamento().toString());
        TxtEdicao.setText(livro.getEdicao().toString());
        CboAutor.setValue(livro.getPrimeiroAutor());
        CboGenero.setValue(livro.getPrimeiroGenero());
    }

    @FXML
    private void LstLivros_Click(MouseEvent evento) {
        exibirLivro();
    }

    @FXML
    private void LstLivros_KeyPressed(KeyEvent evento) {
        exibirLivro();
    }

    @FXML
    private void BtnAdicionar_Click(ActionEvent evento){
        habilitarInterface(true);
        limparTela();
        TxtNome.requestFocus();
    }

    @FXML
    private void BtnDeletar_Click(ActionEvent evento){
        Livro livro = LstLivros.getSelectionModel().getSelectedItem();
        if(livro==null) return;

        try{
            livroDao.excluir(livro);
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
        try{
            Livro livro = new Livro(Integer.parseInt(TxtAnoLanc.getText()),
                    Integer.parseInt(TxtEdicao.getText()),
                    TxtNome.getText());
            livro.getAutores().add(CboAutor.getValue());
            livro.getGeneros().add(CboGenero.getValue());
            livroDao.gravar(livro);
        }catch (NumberFormatException e){
            System.out.println("Erro: Texto em campo de número! Tente novamente.");
        }catch (Exception e){
            System.out.println("Algum erro ocorreu, tente novamente!");
        }finally{
            atualizarLista();
            habilitarInterface(false);
        }
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
