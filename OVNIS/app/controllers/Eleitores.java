package controllers;

import com.google.gson.Gson;
import enums.Status_Eleitor;
import java.util.List;
import models.Eleitor;
import models.Secao;
import play.mvc.Before;
import play.mvc.Controller;

public class Eleitores extends Controller{
    
    private static final Gson g = new Gson();
    
    @Before
    public void loged() {
        if(!Application.loged()){
            renderTemplate("Application/index.html");
        }
    }
    
    public static void cadastro_eleitor() {
        List<Secao> secoes = Secao.findAll();
        Status_Eleitor[] states = Status_Eleitor.values();
        renderTemplate("Eleitores/cadastro_eleitor.html", secoes, states);
    }
    
    public static void salvar_eleitor(Eleitor eleitor, long idSecao) {
        Secao secao = Secao.findById(idSecao);
        eleitor.setSecao(secao);
        eleitor.save();
        renderTemplate("Eleitores/detalhes_eleitor.html", eleitor);
    }
    
    public static void editar_eleitor(String titulo) {
        Eleitor eleitor;
        eleitor = Eleitor.find("byTitulo", titulo).first()  ;
        List<Secao> secoes = Secao.findAll();
        Status_Eleitor[] states = Status_Eleitor.values();
        renderTemplate("Eleitores/cadastro_eleitor.html", eleitor, secoes, states);
    }
    
    public static void listar_eleitores() {
        List<Eleitor> eleitores = Eleitor.findAll();
        render(eleitores);
    }
    
    public static void detalhes_eleitor(String titulo) {
        Eleitor eleitor;
        eleitor = Eleitor.find("byTitulo", titulo).first()  ;
        render(eleitor);
    }
    
    public static void remover_eleitor(long id) {
        Eleitor eleitor = Eleitor.findById(id);
        eleitor.delete();
        listar_eleitores();
    }
    
    public static void desabilitar(String titulo) {
        List<Eleitor> eleitores = Eleitor.findAll();
        for(Eleitor e : eleitores) {
            if(e.getTitulo().equals(titulo)) {
                e.statusEle = Status_Eleitor.INAPTO;
            }
        }
    }
}
