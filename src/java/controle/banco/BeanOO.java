// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2/26/2019 11:45:15 AM
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   BeanOO.java
package controle.banco;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Referenced classes of package controle.banco:
//            Conexao
@ManagedBean(name = "beanOO")
public class BeanOO {

    private static final Logger LOGGER = LogManager.getLogger();
    private ArrayList listaResultado;

    public BeanOO() {
        listaResultado = new ArrayList();
    }

    @PostConstruct
    public void inicio() {
        Conexao con = new Conexao();
        listaResultado = (ArrayList) con.pesquisaOO();
    }

    public ArrayList getListaResultado() {
        return listaResultado;
    }

}
