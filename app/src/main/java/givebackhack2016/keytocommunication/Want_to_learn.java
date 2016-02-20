package givebackhack2016.keytocommunication;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by danielkrajnak on 2/20/16.
 */
@ParseClassName("Want_to_learn")
public class Want_to_learn extends ParseObject {
    public Want_to_learn(){
        //Default constructor
    }


    //Getters and Setters.
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
