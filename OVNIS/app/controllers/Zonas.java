package controllers;

import java.util.List;
import models.Zona;
import play.mvc.Before;
import play.mvc.Controller;

public class Zonas extends Controller{
    
    @Before
    public void loged() {
        if(!Application.loged()){
            renderTemplate("Application/index.html");
        }
    }
    
    public static void cadastro_zona() {
        render();
    }
    
    public static void salvar_zona(Zona zona) {
        zona.save();
        render("Zonas/detalhes_zona.html", zona);
    }
    
    public static void editar_zona(long id) {
        Zona zona = Zona.findById(id);
        render("Zonas/cadastro_zona.html", zona);
    }
    
    public static void detalhes_zona(long id) {
        Zona zona = Zona.findById(id);
        render(zona);
    }
    
    
    public static void listar_zonas() {
        List<Zona> zonas = Zona.findAll();
        render(zonas);
    }
    
    public static void remover_zona(long id) {
        Zona z = Zona.findById(id);
        z.delete();
        listar_zonas();
    }
}
