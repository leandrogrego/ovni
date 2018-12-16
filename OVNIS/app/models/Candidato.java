package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

@Entity
public class Candidato extends Model{
    
    public String nome;
    public int numero;
    public int totalVotos;
    public Long idCargo;
    
    //@ManyToOne
    //@JoinColumn(name="idCargo")
    //public Cargo cargo;
    
    
    @ManyToOne
    @JoinColumn(name="idZona")
    public Zona zona;

    public Candidato(String nome, int numero, Cargo cargo, Zona zona) {
        this.nome = nome;
        this.numero = numero;
        //this.cargo = cargo;
        this.zona = zona;
        this.totalVotos = 0;
    }

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
