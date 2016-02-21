package givebackhack2016.keytocommunication;


import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class App extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        ParseUser.enableAutomaticUser();
        ParseObject.registerSubclass(Language.class);
        ParseObject.registerSubclass(Messages.class);
    }
}
