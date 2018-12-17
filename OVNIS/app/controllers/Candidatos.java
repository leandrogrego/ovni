package controllers;

import java.util.List;
import models.Candidato;
import models.Cargo;
import models.Zona;
import play.mvc.Before;
import play.mvc.Controller;

public class Candidatos extends Controller{
    
    @Before
    public void loged() {
        if(!Application.loged()){
            renderTemplate("Application/index.html");
        }
    }
    
    
    public static void cadastro_candidato() {
        List<Zona> zonas = Zona.findAll();
        List<Cargo> cargos = Cargo.findAll();
        render(zonas, cargos);
    }
    
    
    public static void salvar_candidato(Candidato candidato, Long idZona) {
        Zona zona = null;
        if(idZona != null){
            zona = Zona.findById(idZona);
            
        }
        candidato.setZona(zona);
        candidato.save();
        renderTemplate("Candidatos/detalhes_candidato.html", candidato);
    }
    
    public static void editar_candidato(long id) {
        List<Zona> zonas = Zona.findAll();
        List<Cargo> cargos = Cargo.findAll();
        Candidato candidato = Candidato.findById(id);
        renderTemplate("Candidatos/cadastro_candidato.html", candidato, cargos, zonas);
    }
    
    public static void listar_candidatos() {
        List<Candidato> candidatos = Candidato.findAll();
        render(candidatos);
    }
    
    public static void remover_candidato(long id) {
        Candidato candidato = Candidato.findById(id);
        candidato.delete();
        listar_candidatos();
    }
    
    public static void detalhes_candidato(Long id) {
        Candidato candidato = Candidato.findById(id);
        Cargo cargo = Cargo.findById(candidato.idCargo);
        render(candidato, cargo);
    }
}
