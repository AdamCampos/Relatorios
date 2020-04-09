// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2/26/2019 3:46:41 PM
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   EntidadeOverride.java
package modelo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectManyCheckbox;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
@Table(name = "OverrideAdam")
@ManagedBean(name = "override")
@ViewScoped
public class EntidadeOverride implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger();
    @Id
    private Long id;
    @Column(name = "Tag")
    private String tag;
    @Column(name = "TempoAtivado")
    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    private Date tempoAtivado;
    @Column(name = "TempoDesativado")
    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    private Date tempoDesativado;
    @Column(name = "Ativo", nullable = true)
    private Boolean ativo;
    @Transient
    @Column(name = "TempoEmOverride", nullable = true)
    private int tempoEmOverride;
    @Column(name = "MotivoAtivar")
    private String motivoAtivar;
    @Column(name = "MotivoDesativar")
    private String motivoDesativar;
    @Column(name = "Tipo")
    private String tipo;
    @Column(name = "Acao")
    private String acao;
    @Transient
    private String tempoAtivadoStr;
    @Transient
    String tempoDesativadoStr;
    @Transient
    private ArrayList listaFiltrada;
    @Transient
    private ArrayList listaAtivado;
    @Transient
    private ArrayList listaAtivos;
    @Transient
    private final ArrayList listaTipoOverride = new ArrayList();
    @Transient
    private String ativoStr;
    @Transient
    private long tempoRestante;
    @Transient
    private boolean mostrarMotivoInibicao;
    @Transient
    private String radioSelected;
    @Transient
    private String paginaConteudo;

    public EntidadeOverride() {
        ativo = Boolean.valueOf(false);
        listaFiltrada = new ArrayList();
        listaAtivado = new ArrayList();
        listaAtivos = new ArrayList();
        mostrarMotivoInibicao = true;
        listaAtivos.clear();
        listaAtivos.add("Normal");
        listaAtivos.add("Ativo");
    }

    public ArrayList getListaAtivos() {
        return listaAtivos;
    }

    public ArrayList getListaTipoOverride() {
        listaTipoOverride.clear();
        listaTipoOverride.add("Operação");
        listaTipoOverride.add("Manutenção");
        listaTipoOverride.add("Lógica CSS");
        listaTipoOverride.add("Lógica pacote");
        return listaTipoOverride;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getTempoAtivado() {
        return tempoAtivado;
    }

    public void setTempoAtivado(Date tempoAtivado) {
        try {
            this.tempoAtivado = tempoAtivado;
        } catch (Exception exception) {
        }
    }

    public Date getTempoDesativado() {
        return tempoDesativado;
    }

    public void setTempoDesativado(Date tempoDesativado) {
        try {
            this.tempoDesativado = tempoDesativado;
        } catch (Exception e) {
            LOGGER.error("Erro no setDesativado()");
        }
    }

    public int getTempoEmOverride() {
        return tempoEmOverride;
    }

    public void setTempoEmOverride(int tempoEmOverride) {
        try {
            this.tempoEmOverride = tempoEmOverride;
        } catch (Exception exception) {
        }
    }

    public String getMotivoAtivar() {
        return motivoAtivar;
    }

    public void setMotivoAtivar(String motivoAtivar) {
        this.motivoAtivar = motivoAtivar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotivoDesativar() {
        return motivoDesativar;
    }

    public void setMotivoDesativar(String motivoDesativar) {
        this.motivoDesativar = motivoDesativar;
    }

    public ArrayList getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(ArrayList listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public String getTempoAtivadoStr() {
        try {
            DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy >> HH:mm");
            tempoAtivadoStr = formataData.format(getTempoAtivado());
            return tempoAtivadoStr;
        } catch (Exception e) {
            return "";
        }
    }

    public void setTempoAtivadoStr(String tempoAtivadoStr) {
        this.tempoAtivadoStr = tempoAtivadoStr;
    }

    public String getTempoDesativadoStr() {
        DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy >> HH:mm");
        try {
            tempoDesativadoStr = formataData.format(getTempoDesativado());
            return tempoDesativadoStr;
        } catch (Exception e) {
            LOGGER.error("Erro na obtenção do horário");
        }
        return " ";
    }

    public void setTempoDesativadoStr(String tempoDesativadoStr) {
        this.tempoDesativadoStr = tempoDesativadoStr;
    }

    public long getTempoRestante() {
        Date atual = Calendar.getInstance().getTime();
        long minutos = (atual.getTime() - getTempoAtivado().getTime()) / 1000L / 60L;
        if (getTempoDesativado() != null) {
            minutos = 0x98967fL;
        }
        return minutos;
    }

    private void setTempoRestante(long tempo) {
        tempoRestante = tempo;
    }

    private long getTempoRestante(boolean b) {
        return tempoRestante;
    }

    public String getTempoRestanteStr() {
        try {
            if (getTempoRestante() == 0x98967fL) {
                return "Normalizado";
            }
        } catch (Exception e) {
            return "";
        }
        if (getTipo().equals("Opera\347\343o") && getMotivoAtivar() != null) {
            try {
                DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy >> HH:mm");
                long tempoAtualMinutos = System.currentTimeMillis() / 1000L / 60L;
                long restante = Long.parseLong(getMotivoAtivar()) - getTempoRestante();
                long previsaoTerminoMilisegundos = 60000L * (tempoAtualMinutos + restante);
                String previsaoStr = formataData.format(Long.valueOf(previsaoTerminoMilisegundos));
                setTempoRestante(restante);
                return (new StringBuilder()).append(String.valueOf(restante)).append(" min. ").append(previsaoStr).toString();
            } catch (Exception e) {
                return "Erro";
            }
        }
        if (getTipo().equals("Opera\347\343o") && getMotivoAtivar() == null) {
            return "IMPLEMENTAR -> ADAM";
        }
        return (new StringBuilder()).append("Manuten\347\343o: ").append(String.valueOf((new StringBuilder()).append(getTempoRestante() / 60L).append("h").toString())).toString();
    }

    public String getMotivoAtivadoStr() {
        try {
            if (getTipo().contains("Opera\347\343o")) {
                return "Override operacional";
            }
        } catch (Exception e) {
            return "";
        }
        return getMotivoAtivar();
    }

    public String getTempoOverrideAtivado() {
        try {
            if (getTempoRestante() == 0x98967fL) {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
        return (new StringBuilder()).append(String.valueOf(getTempoRestante() / 60L)).append("h").toString();
    }

    public Boolean getAtivo() {
        if (ativo != null) {
            return ativo;
        } else {
            return ativo = Boolean.valueOf(false);
        }
    }

    public void setAtivo(Boolean ativo) {
        try {
            this.ativo = ativo;
        } catch (Exception e) {
            this.ativo = Boolean.valueOf(false);
        }
    }

    public String getOOAtivoStr() {
        if (getAtivo().booleanValue() && getTempoRestante(true) > 0L) {
            ativoStr = String.valueOf("Ativo").trim();
            return ativoStr;
        } else {
            return ativoStr = "Normal";
        }
    }

    public String getOMAtivoStr() {
        if (getAtivo().booleanValue()) {
            ativoStr = String.valueOf("Ativo").trim();
            return ativoStr;
        } else {
            return ativoStr = "Normal";
        }
    }

    public void setAtivoStr(String ativoStr) {
        this.ativoStr = ativoStr;
    }

    public List getListaAtivado() {
        listaAtivado.clear();
        listaAtivado.add("NORMAL");
        listaAtivado.add("ATIVADO");
        return listaAtivado;
    }

    public void setListaAtivado(ArrayList listaAtivado) {
        this.listaAtivado = listaAtivado;
    }

    public boolean getMostrarMotivoInibicao() {
        return mostrarMotivoInibicao;
    }

    public void setMostrarMotivoInibicao(boolean mostrarMotivoInibicao) {
        this.mostrarMotivoInibicao = mostrarMotivoInibicao;
    }

    public String toString() {
        try {
            return (new StringBuilder()).append("[TAG: ").append(getTag()).append("]").toString();
        } catch (Exception e) {
            return (new StringBuilder()).append("").append(hashCode()).toString();
        }
    }

    public void listenerCheck(AjaxBehaviorEvent objeto) {
        if (objeto.getSource() instanceof HtmlSelectManyCheckbox) {
            HtmlSelectManyCheckbox source = (HtmlSelectManyCheckbox) objeto.getSource();
            Object listaObjetosHTML[] = source.getSelectedValues();
            Object aobj[] = listaObjetosHTML;
            int i = aobj.length;
            for (int j = 0; j < i; j++) {
                Object o = aobj[j];
                LOGGER.info((new StringBuilder()).append("Objeto: ").append(o).toString());
                if (o.toString().contains("Operação")) {
                    setMostrarMotivoInibicao(false);
                } else {
                    setMostrarMotivoInibicao(true);
                }
            }

        }
    }

    public String getRadioSelected() {
        return radioSelected;
    }

    public void setRadioSelected(String radioSelected) {
        LOGGER.debug((new StringBuilder()).append("Set.: ").append(radioSelected).toString());
        String s = radioSelected;
        byte byte0 = -1;
        switch (s.hashCode()) {
            case 2526:
                if (s.equals("OM")) {
                    byte0 = 0;
                }
                break;

            case 2528:
                if (s.equals("OO")) {
                    byte0 = 1;
                }
                break;

            case -691931636:
                if (s.equals("OMInativo")) {
                    byte0 = 2;
                }
                break;

            case -1501278262:
                if (s.equals("OOInativo")) {
                    byte0 = 3;
                }
                break;
        }
        switch (byte0) {
            case 0: // '\0'
                LOGGER.debug("OMAtivo!!");
                setPaginaConteudo("/PAGES/om.xhtml");
                break;

            case 1: // '\001'
                LOGGER.debug("OOAtivo!!");
                setPaginaConteudo("/PAGES/oo.xhtml");
                break;

            case 2: // '\002'
                LOGGER.debug("OMInativo!!");
                break;

            case 3: // '\003'
                LOGGER.debug("OOInativo!!");
                break;
        }
        this.radioSelected = radioSelected;
    }

    public String getPaginaConteudo() {
        return paginaConteudo;
    }

    public void setPaginaConteudo(String paginaConteudo) {
        LOGGER.debug((new StringBuilder()).append("Página selecionada: ").append(paginaConteudo).toString());
        this.paginaConteudo = paginaConteudo;
    }

}
