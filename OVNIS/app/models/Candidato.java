package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Candidato extends Model{
    public Blob foto;
    public String nome;
    public int numero;
    public int totalVotos;
    public int contagem;
    public Long idCargo;
    public String vice;
    
    //@ManyToOne
    //@JoinColumn(name="idCargo")
    //public Cargo cargo;
    
    
    @ManyToOne
    @JoinColumn(name="idZona")
    public Zona zona;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    
    public void setCargo(Cargo cargo) {
        //this.cargo = cargo;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
    

}
