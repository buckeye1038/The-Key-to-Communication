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
        Parse.initialize(this, "kouQKM97bx7TXwDwd7DVyVIH246em5LUQklGvyMb", "Q4f68F98Zo3Bixln5hbn5scYa0tuM4buRZ0g7Y8I");
        ParseUser.enableAutomaticUser();
        ParseObject.registerSubclass(Language.class);
        ParseObject.registerSubclass(Messages.class);
    }
}
