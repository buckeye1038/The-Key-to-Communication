package givebackhack2016.keytocommunication;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Language")
public class Language extends ParseObject{
    String nameOfLanguage;
    public Language(){
        // Default constructor
    }

    public Language(String lang){
        put("langName", lang);
    }

    public String getLanguage() {
        return getString("langName");
    }
}
