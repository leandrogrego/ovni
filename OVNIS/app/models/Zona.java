package models;

import java.util.List;
import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Zona extends Model{
    
    private String nome;
    private int numZona;
    
    public Zona(String nome, int numZona) {
        this.nome = nome;
        this.numZona = numZona;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumZona() {
        return numZona;
    }

    public void setNumZona(int numZona) {
        this.numZona = numZona;
    }

    
    public List<Secao> getSecoes() {
        List<Secao> secoes = Secao.find("byIdZona", this.id).fetch();
        return secoes;
    }
    
    public Candidato getCandidato(int numero, int idCargo){
        Candidato candidato = Candidato.find("numero = ? AND idcargo = ? AND (idzona = ? OR idzona = null)", numero, idCargo, this.id).first();
        //candidato = Candidato.find("byNumero", numero).first();
        System.out.println(numero +", "+ idCargo + ", "+ this.id);
        return candidato;
    }
}
