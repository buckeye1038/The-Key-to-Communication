package givebackhack2016.keytocommunication;

import com.parse.ParseObject;
import com.parse.ParseClassName;

/**
 * Created by danielkrajnak on 2/20/16.
 */
@ParseClassName("Known_Languages")
public class Known_Languages extends ParseObject{
    public Known_Languages(){
      //Default Constructor
    }

    //Getters and setters
    public User getUser(){
        return new User(getParseUser("user"));
    }

    public void setUser( User user){
        put("user", user.getParseUser());
    }

    public void setLanguage(String language){
        put("langName", language);
    }

    public String getLanguage(){
        return  getString("langName");
    }

}
