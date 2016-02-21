package givebackhack2016.keytocommunication;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.sql.Time;
import java.util.Date;

/**
 * Created by danielkrajnak on 2/20/16.
 */
@ParseClassName("Messages")
public class Messages extends ParseObject{
    public Messages(){
        //Default Constructor
    }

    /*
    Get and Set
     */

    //TimeSent
    public Date getTimeSent(){
        return getDate("timeSent");
    }
    public void setTimeSent(Date date){
        put("timeSent", date);
    }

    //Language
    public String getLanguage(){
        return getString("language");
    }
    public void setLanguage(Language language){
        put("language", language);
    }

    //SentTo
    public User getSentTo(){
        return new User(getParseUser("sentTo"));
    }
    public void setSentTo(User user){
        put("sentTo", user);
    }

    //SentBy
    public User getSentBy(){
        return new User(getParseUser("sentBy"));
    }
    public void setSentyBy(User user){
        put("sentBy", user);
    }

    //Message
    public String getMessage(){
        return getString("message");
    }
    public void setMessage(String message){
        put("message", message);
    }






}
