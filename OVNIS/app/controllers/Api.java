package controllers;

import models.Voto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import enums.Status_Eleitor;
import enums.Status_Voto;
import java.util.List;
import models.Candidato;
import models.Cargo;
import models.Eleitor;
import models.Loger;
import models.Secao;
import models.Zona;
import play.mvc.Controller;

public class Api extends Controller{
    
    private static final Gson g = new GsonBuilder().setPrettyPrinting().create();
    
    public static void getEleitor(String titulo) {
        Eleitor eleitor = Eleitor.find("byTitulo", titulo).first();
        renderJSON(g.toJson(eleitor));
    }
    
    public static void justificar(String titulo) {
        Eleitor e = Eleitor.find("byTitulo", titulo).first();
        if(e != null 
                && e.statusVot == Status_Voto.NAO_VOTOU  
                && e.statusEle == Status_Eleitor.APTO)
        {
            e.statusVot = Status_Voto.JUSTIFICOU;
            e.save();
            new Loger("justificar "+titulo);
            renderJSON(g.toJson(e));
        } else {
            Mensagem RESULT = new Mensagem("Erro","O Eleitor já votou ou justificou ou está inápto");
            renderJSON(g.toJson(RESULT));
        }
    }
    
    public static void getCandidato(long idSecao, int numero, int idCargo) {
        Secao secao = Secao.findById(idSecao);
        Zona zona = secao.getZona();
        Candidato candidato = zona.getCandidato(numero, idCargo);
        if(candidato != null){
            renderJSON(g.toJson(candidato));
        } else {
            renderJSON(g.toJson(new Mensagem("Error","Candidato Não Econtrado")));
        }
    }
    
    public static void getUrna(long idSecao) {
        Secao secao = Secao.findById(idSecao);
        renderJSON(g.toJson(new Mensagem("ipUrna",secao.getIpUrna())));
    }
    
    public static void setUrna(long idSecao, String ipUrna) {
        Secao secao = Secao.findById(idSecao);
        Mensagem RESULT;
        if(secao.getIpUrna() == null || "".equals(secao.getIpUrna())){
            secao.setIpUrna(ipUrna);
            secao.save();
            RESULT = new Mensagem("Success","Seção vinculada com sucesso");
            new Loger("setUrna: idSecao="+idSecao+": ipUrna="+ipUrna);
        } else {
            RESULT = new Mensagem("Error","Seção já vinculada a outra urna");
        }
        renderJSON(g.toJson(RESULT));
    }
    
    public static void setTerminal(long idSecao, String ipTerminal) {
        Secao secao = Secao.findById(idSecao);
        Mensagem RESULT;
        if(secao.getIpTerminal() == null || "".equals(secao.getIpTerminal())){
            secao.setIpTerminal(ipTerminal);
            secao.save();
            RESULT = new Mensagem("Success","Seção vinculada com sucesso");
            new Loger("setTerminal: idSecao="+idSecao+": ipUrna="+ipTerminal);
        } else {
            RESULT = new Mensagem("Erro","Seção já vinculada a outro terminal");
        }
        renderJSON(g.toJson(RESULT));
    }
    
    public static void getTerminal(long idSecao) {
        Secao secao = Secao.findById(idSecao);
        Mensagem RESULT = new Mensagem("ipTerminal",secao.getIpTerminal());
        renderJSON(g.toJson(RESULT));
    }
    
    public static void setStatusEleitor(String titulo, long idSecao) {
        Secao secao = Secao.findById(idSecao);
        if(secao.bloqueio) {
            Mensagem RESULT = new Mensagem("Error","Votação não iniciada ou Encerrada");
            renderJSON(g.toJson(RESULT));
        } else {
            Eleitor eleitor = Eleitor.find("byTitulo", titulo).first();
            if(eleitor.statusEle.equals(Status_Eleitor.APTO) && eleitor.statusVot.equals(Status_Voto.NAO_VOTOU)){
                eleitor.statusVot = Status_Voto.VOTOU;
                eleitor.save();
                new Loger("setStatusEleitor: idSecao="+idSecao+": titulo="+titulo);
                renderJSON(g.toJson(eleitor));
             } else {
                Mensagem RESULT = new Mensagem("Error","Eleitor ja votou ou jutificou ou está inápto.");
                renderJSON(g.toJson(RESULT));
            }
        }
    }
    
    public static void setVotoEleitor(int numCandidato, int idCargo, String ipUrna) {
        Secao secao = Secao.find("byIpUrna", ipUrna).first();
        if(secao == null){
            renderJSON(g.toJson(new Mensagem("Error", "Seção Não encontrada!")));
        } else {
            Voto voto;
            if(secao!=null && secao.bloqueio) {
                Mensagem RESULT = new Mensagem("Error","Votação não iniciada ou Encerrada");
                renderJSON(g.toJson(RESULT));
            } else {
                Candidato candidato = secao.zona.getCandidato(numCandidato, idCargo);
                if(candidato != null){
                    voto = new Voto(candidato, secao);
                    candidato.totalVotos++;
                    candidato.save();
                    new Loger("setVotoEleitor: numCandidato="+numCandidato+": idCargo="+idCargo);
                } else {
                    voto = new Voto(null, secao);
                }
                voto.save();
                renderJSON(g.toJson(voto));
            }
        }
    }
    
    public void getCargos(){
        List<Cargo> cargos = Cargo.findAll();
        renderJSON(g.toJson(cargos));
    }
    
    public void getCandidatos(){
        List<Candidato> candidatos = Candidato.findAll();
        renderJSON(g.toJson(candidatos));
    }
    
    public void resetAll(){
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
            s.ipUrna=null;
            s.ipTerminal=null;
            s.save();
        });
    }
    
    public void log(){
        List<Loger> logs = Loger.findAll();
        renderJSON(g.toJson(logs));
    }
    
    
    private static class Mensagem{
        String key;
        String value;
        public Mensagem(String key, String value){
            this.key=key;
            this.value=value;
        }
    }
}