package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Boletim extends Model{
    
    public int votoBranco;
    public int votoNulo;
    public int votoValido;
    public List<Candidato> candidatos = new ArrayList();

    public Boletim(int votoBranco, int votoNulo, int votoValido, List<Candidato> candidatos) {
        this.votoBranco = votoBranco;
        this.votoNulo = votoNulo;
        this.votoValido = votoValido;
        this.candidatos = candidatos;
    }

    @Override
    public String toString() {
        return "Boletim{" + "votoBranco=" + votoBranco + ", votoNulo=" + votoNulo + ", votoValido=" + votoValido + ", candidatos=" + candidatos + '}';
    }
    
    
}
