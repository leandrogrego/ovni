package models;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Loger extends Model{

    private Date data;
    private String log;
    
    public Loger(String text) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        data = new Date();
        data.getTime();
        //sdf.format(data);
        log = text;
        this.save();
    }
}


