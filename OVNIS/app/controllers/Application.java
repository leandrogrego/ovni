package controllers;

import play.mvc.*;
import play.cache.Cache;

public class Application extends Controller {
    private final String chave = "myaccess";
    
    public void index() {
        loged();
        render();
    }
    public void api() {
        render();
    }
    
    public void gestor() {
        if(!loged()){
            index();
        } else {
            render();
        }
    }
    
    public void login(String chave) {
        if(chave.equals(this.chave)){
            Cache.set(session.getId()+"-login", this.chave, "30mn");
            loged();
            renderTemplate("Application/gestor.html");
        } else renderTemplate("Application/index.html");
    }
    
    protected static boolean loged() {
        if (Cache.get(session.getId()+"-login", String.class)!=null){
            flash.put("loged","loged");
            return true;
        } else {
            flash.discard("loged");
            return false;
        }
    }
    
    public void logout() {
        Cache.delete(session.getId()+"-login");
        flash.discard("loged");
        renderTemplate("Application/index.html", loged());
    }
    
}