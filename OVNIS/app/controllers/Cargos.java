package controllers;

import java.util.List;
import models.Cargo;
import play.mvc.Before;
import play.mvc.Controller;

public class Cargos extends Controller{
    
    @Before
    public void loged() {
        if(!Application.loged()){
            renderTemplate("Application/index.html");
        }
    }
    
    public static void cadastro_cargo() {
        render();
    }
    
    public static void salvar_cargo(Cargo cargo) {
        cargo.save();
        renderTemplate("Cargos/detalhes_cargo.html", cargo);
    }
    
    public static void editar_cargo(long id) {
        Cargo cargo = Cargo.findById(id);
        renderTemplate("Cargos/cadastro_cargo.html", cargo);
    }
    
    public static void listar_cargos() {
        List<Cargo> cargos = Cargo.findAll();
        render(cargos);
    }
    
    public static void remover_cargo(long id) {
        Cargo c = Cargo.findById(id);
        c.delete();
        listar_cargos();
    }
    
    public static void detalhes_cargo(long id) {
        Cargo cargo = Cargo.findById(id);
        render(cargo);
    }
    
}
