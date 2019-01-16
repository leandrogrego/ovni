package controllers;

import enums.Status_Voto;
import java.util.List;
import models.Candidato;
import models.Eleitor;
import models.Secao;
import models.Voto;
import models.Zona;
import play.mvc.Before;
import play.mvc.Controller;

public class Secoes extends Controller{

    @Before
    public void loged() {
        if(!Application.loged()){
            renderTemplate("Application/index.html");
        }
    }
    
    public static void cadastro_secao() {
        List<Zona> zonas = Zona.findAll();
        render(zonas);
    }
    
    public static void salvar_secao(Secao secao, long idZona) {
        Zona zona = Zona.findById(idZona);
        secao.setZona(zona);
        secao.save();
        renderTemplate("Secoes/detalhes_secao.html", secao);
    }
    
    public static void editar_secao(long id) {
        Secao secao = Secao.findById(id);
        List<Zona> zonas = Zona.findAll();
        renderTemplate("Secoes/cadastro_secao.html", secao, zonas);
    }
    
    public static void detalhes_secao(long id) {
        Secao secao = Secao.findById(id);
        render(secao);
    }
    
    
    public static void listar_secoes() {
        List<Secao> secoes = Secao.findAll();
        render(secoes);
    }
    
    public static void remover_secao(long id) {
        Secao s = Secao.findById(id);
        s.delete();
        listar_secoes();
    }
    
    public static void setUrna(long id, String ip) {
        Secao secao = Secao.findById(id);
        secao.setIpUrna(ip);
        secao.save();
        renderTemplate("Secoes/detalhes_secao.html", secao);
    }
    
    public static void setTerminal(long id, String ip) {
        Secao secao = Secao.findById(id);
        secao.setIpTerminal(ip);
        secao.save();
        renderTemplate("Secoes/detalhes_secao.html", secao);
    }
    
    public static void resetUrna(long id) {
        Secao secao = Secao.findById(id);
        secao.setIpUrna("");
        secao.save();
        renderTemplate("Secoes/detalhes_secao.html", secao);
    }
    
    public static void resetTerminal(long id) {
        Secao secao = Secao.findById(id);
        secao.setIpTerminal("");
        secao.save();
        renderTemplate("Secoes/detalhes_secao.html", secao);
    }
    
        public static void startEleicao(){
        List<Candidato> candidatos = Candidato.findAll();
        candidatos.forEach(c -> {
            c.totalVotos=0;
            c.save();
        });
        List<Voto> votos = Voto.findAll();
        votos.forEach(v -> {
            v.delete();
        });
        List<Eleitor> eleitores = Eleitor.findAll();
        eleitores.forEach(e -> {
            e.statusVot=Status_Voto.NAO_VOTOU;
            e.save();
        });
        List<Secao> secoes = Secao.findAll();
        secoes.forEach(s -> {
            s.bloqueio=false;
            s.ipUrna=null;
            s.ipTerminal=null;
            s.save();   
        });  
        listar_secoes();
    }
        
    public static void endEleicao(){
        List<Secao> secoes = Secao.findAll();
        secoes.forEach(s -> {
            s.bloqueio=true;
            s.save();
        });
        listar_secoes();
    }
}
