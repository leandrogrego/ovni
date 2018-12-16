package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Cargo extends Model{
    
    private String nome;
    
    //@OneToMany(mappedBy="idCargo")
    //public List<Candidato> candidatos;
    
    @OneToMany(mappedBy="idCargo", cascade = CascadeType.ALL)
    public List<Candidato> candidatos;
    
    public Cargo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public List<Candidato> getCandidatos() {
        return this.candidatos;
    }
    
}
