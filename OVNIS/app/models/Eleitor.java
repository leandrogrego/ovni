package models;

import enums.Status_Eleitor;
import enums.Status_Voto;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

@Entity
public class Eleitor extends Model{
    
    private String nome;
    private String titulo;
    
    @ManyToOne
    @JoinColumn(name="idSecao")
    private Secao secao;
    
    public Status_Eleitor statusEle;
    public Status_Voto statusVot;

    public Eleitor(String nome, String titulo, Secao secao) {
        this.nome = nome;
        this.titulo = titulo;
        this.secao = secao;
        this.statusEle = Status_Eleitor.APTO;
        this.statusVot = Status_Voto.NAO_VOTOU;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }
    
    public Status_Eleitor getStatusEleitor(Secao secao) {
        return statusEle;
    }
    
    public Status_Voto getStatusVoto() {
        return statusVot;
    }
    
}
    