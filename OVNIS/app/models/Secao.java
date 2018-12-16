package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

@Entity
public class Secao extends Model{
    
    public  String nome;
    public int numSecao;
    public String ipTerminal;
    public String ipUrna;
    public boolean bloqueio;
    
    @ManyToOne
    @JoinColumn(name="idZona")
    public Zona zona;
    
    public Secao(int idZona, int numSecao, String nome) {
        this.numSecao = numSecao;
        this.nome = nome;
        this.zona = Zona.findById(idZona);
    }

    public int getNumSecao() {
        return numSecao;
    }

    public void setNumSecao(int numSecao) {
        this.numSecao = numSecao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public String getIpTerminal() {
        return ipTerminal;
    }
    
    public void setIpTerminal(String ipTerminal) {
        this.ipTerminal = ipTerminal;
    }
    
    public String getIpUrna() {
        return ipUrna;
    }
    
    public void setIpUrna(String ipUrna) {
        this.ipUrna = ipUrna;
    }
    
    public List<Eleitor> getEleitores(){
        List<Eleitor> eleitores = Eleitor.find("byIdSecao", this.id).fetch();
        return eleitores;
    }
}
