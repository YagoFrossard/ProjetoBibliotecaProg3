package br.edu.femass.projetobiblioteca.dao;

import br.edu.femass.projetobiblioteca.model.Autor;
import br.edu.femass.projetobiblioteca.model.Genero;
import br.edu.femass.projetobiblioteca.model.Livro;
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
        xstream.alias("Autor", Autor.class);
        xstream.alias("Genero", Genero.class);
        xstream.alias("Livro", Livro.class);
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);
        xstream.allowTypes(new Class[] {Autor.class, Genero.class, Livro.class});
        livros = (List<Livro>) xstream.fromXML(new File("livros.xml"));
        return livros;
    }

    @Override
    public void excluir(Livro objeto) {
        livros.remove(objeto);
        updateXML();
    }
}
