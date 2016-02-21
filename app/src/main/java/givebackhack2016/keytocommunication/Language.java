package givebackhack2016.keytocommunication;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by danielkrajnak on 2/20/16.
 */
@ParseClassName("Language")
public class Language extends ParseObject{

    public Language(){

    }

    public String getLanguage() {
        return getString("langName");
    }

    public void setLanguage(String lang){
        put("langName", lang);
    }

}
