package br.edu.femass.projetobiblioteca.dao;

import br.edu.femass.projetobiblioteca.model.Autor;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AutorDao implements Dao<Autor> {

    private final XStream xstream = new XStream();
    private static List<Autor> autores = new ArrayList<>();

    public void updateXML(){
        String xml = xstream.toXML(autores);
        try {
            FileWriter fw = new FileWriter("autores.xml");
            fw.write(xml);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gravar(Autor objeto){
        autores.add(objeto);
        updateXML();
    }

    @Override
    public List<Autor> listar(){
        xstream.alias("Autor", Autor.class);
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);
        xstream.allowTypes(new Class[] {Autor.class});
        autores = (List<Autor>) xstream.fromXML(new File("autores.xml"));
        return autores;
    }

    @Override
    public void excluir(Autor objeto){
        autores.remove(objeto);
        updateXML();
    }
}
