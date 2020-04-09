// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2/26/2019 11:44:20 AM
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Bean.java
package controle.banco;

import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Referenced classes of package controle.banco:
//            Conexao
@ManagedBean(name = "bean")
public class Bean {

    private static final Logger LOGGER = LogManager.getLogger();
    private ArrayList listaResultado;
    private static long tempoInicial;
    private static long tempoFinal;
    private long intervalo;

    public Bean() {
        listaResultado = new ArrayList();
    }

    @PostConstruct
    public void inicio() {
        tempoInicial = Calendar.getInstance().getTime().getTime();
        Conexao con = new Conexao();
        listaResultado = (ArrayList) con.pesquisa();
        tempoFinal = Calendar.getInstance().getTime().getTime();
        intervalo = Math.abs(tempoInicial - tempoFinal) / 1000L;
    }

    public ArrayList getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(ArrayList listaResultado) {
        this.listaResultado = listaResultado;
    }

    public Long getIntervalo() {
        return Long.valueOf(intervalo);
    }

    public void setIntervalo(Long intervalo) {
        this.intervalo = intervalo.longValue();
    }

    public long getTempoInicial() {
        return tempoInicial;
    }

    public void setTempoInicial(long tempoInicial) {
        Bean _tmp = this;
        tempoInicial = tempoInicial;
    }

    public void testeListener() {
        LOGGER.debug("Teste listener js ok!");
    }

}
