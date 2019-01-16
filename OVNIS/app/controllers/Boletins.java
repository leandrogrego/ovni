package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import models.Boletim;
import play.libs.WS;
import play.mvc.Controller;

public class Boletins extends Controller{
    
    public static void consumoBoletim(int ipUrna) {
        
         WS.HttpResponse res = WS.url("https://urna-api.herokuapp.com/api/" + ipUrna).get();
         
         if(res.success()) {
             Gson gson = new Gson();
             JsonElement type = res.getJson();
             Boletim boletim = gson.fromJson(type, Boletim.class);
             render(boletim);
         
         } else {
             
             renderText("Erro: " + res.getStatusText());  
         }
    }
}
