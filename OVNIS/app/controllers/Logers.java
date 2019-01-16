package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Loger;
import play.mvc.Controller;

public class Logers extends Controller{
    
    public static void index(){
        render();
    }
    
    public static void buscaLogDate(String data) {
        
        List<Loger> logers = Loger.find("data LIKE '"+data+"%'").fetch();
        renderTemplate("Logers/index.html",logers);
    }
    
    public static void buscaLogId(Long id) {
        Loger loger = Loger.findById(id);
        List<Loger> logers = new ArrayList();
        logers.add(loger);
        renderTemplate("Logers/index.html",logers);
    }
    
}
