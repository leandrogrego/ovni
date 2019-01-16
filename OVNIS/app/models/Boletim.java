package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import play.db.jpa.Model;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Boletim extends Model{
    
    public int votoBranco;
    public int votoNulo;
    public int votoValido;
    

    //@OneToMany(mappedBy="idBoletim", cascade = CascadeType.ALL)
    @ManyToMany
    @JoinTable(name="boletim_candidato", joinColumns=
      {@JoinColumn(name="idBoletim")}, inverseJoinColumns=
        {@JoinColumn(name="idCandidato")})
    public List<Candidato> candidatos;


    @Override
    public String toString() {
        return "Boletim{" + "votoBranco=" + votoBranco + ", votoNulo=" + votoNulo + ", votoValido=" + votoValido + ", candidatos=" + candidatos + "}";
    }
    
    
}
