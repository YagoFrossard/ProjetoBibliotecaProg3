package br.edu.femass.projetobiblioteca.dao;

import br.edu.femass.projetobiblioteca.model.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LivroDao implements Dao<Livro> {

    private final XStream xstream = new XStream();
    private static List<Livro> livros = new ArrayList<>();

    public void updateXML(){
        String xml = xstream.toXML(livros);
        try {
            FileWriter fw = new FileWriter("livros.xml");
            fw.write(xml);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gravar(Livro objeto) {
        livros.add(objeto);
        updateXML();
    }

    @Override
    public List<Livro> listar() {
        xstream.alias("Copia", Copia.class);
        xstream.alias("Autor", Autor.class);
        xstream.alias("Genero", Genero.class);
        xstream.alias("LivroOriginal", Livro.class);
        xstream.alias("Aluno", Aluno.class);
        xstream.alias("Professor", Professor.class);
        xstream.alias("Empréstimo", Emprestimo.class);
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);
        xstream.allowTypes(new Class[] {Livro.class, Genero.class, Autor.class, Copia.class, Usuario.class,
                Professor.class, Aluno.class, Emprestimo.class});
        livros = (List<Livro>) xstream.fromXML(new File("livros.xml"));
        return livros;
    }

    @Override
    public void excluir(Livro objeto) {
        livros.remove(objeto);
        updateXML();
    }
}
