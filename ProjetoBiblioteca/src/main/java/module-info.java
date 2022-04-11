module br.edu.femass.projetobiblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;

    exports br.edu.femass.projetobiblioteca;
    exports br.edu.femass.projetobiblioteca.gui;
    exports br.edu.femass.projetobiblioteca.model;
    exports br.edu.femass.projetobiblioteca.dao;
    opens br.edu.femass.projetobiblioteca.gui to javafx.fxml;
    opens br.edu.femass.projetobiblioteca.model to xstream;
    opens br.edu.femass.projetobiblioteca.dao to xstream;
}