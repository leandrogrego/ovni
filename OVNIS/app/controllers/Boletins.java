package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import models.Boletim;
import models.Candidato;
import models.Secao;
import models.Voto;
import play.libs.WS;
import play.mvc.Controller;

public class Boletins extends Controller{
    
    public static void index(Long idSecao){
        Boletim boletim = null;
        Secao secao = Secao.findById(idSecao);
        if(secao != null && secao.ipUrna!=null && !"".equals(secao.ipUrna)){
           boletim = consumoBoletim(secao.ipUrna);
           if(boletim.candidatos!=null){
                boletim.id=secao.id;
                boletim.save();   
                for(Candidato c : boletim.candidatos){
                    c.contagem = Voto.getVotosCandidato(c.id);
                }
            }
        }
        render(boletim);
    }
    
    public static Boletim consumoBoletim(String ipUrna) {
         WS.HttpResponse res = WS.url("http://urna-api.herokuapp.com/api/boletim/" + ipUrna).get();
         if(res.success()) {
            Gson gson = new Gson();
            JsonElement type = res.getJson();
            Boletim boletim = gson.fromJson(type, Boletim.class);
            return boletim;
         } else {
             return null;  
         }
        
    }
}
