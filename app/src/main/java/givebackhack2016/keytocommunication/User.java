package givebackhack2016.keytocommunication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.SignUpCallback;


public class User {
    private ParseUser user;

    public User(){
        user = new ParseUser();
    }

    public User(ParseUser parseUser) {
        user = parseUser;
    }

    //Get methods
    public String getUserID(){
        return user.getString("ID");
    }

    public ParseUser getParseUser() {
        return user;
    }

    public String getFirstName(){
        return user.getString("firstName");
    }

    public String getLastName(){
        return user.getString("firstName");
    }

    public String getTagLine(){
        return user.getString("tagLine");
    }

    public int getDOB(){
        return user.getInt("DOB");
    }

    public String getUsername() { return user.getUsername(); }

    public String getLanguageWantToLearn(){ return user.getString("wantToLearn");}

    public String getLanguageKnows(){ return user.getString("knows");}
    //set methods

    //Setting the Username
    public void setUsername(String username){
        user.setUsername(username);
    }
    //Setting the password
    public void setPassword(String password){
        user.setPassword(password);
    }
    //Set first name of the User
    public void setFirstName(String fName){
        user.put("firstName", fName);
    }
    //Setting the last name of the User
    public void setLastName(String lName){
        user.put("lastName", lName);
    }

    //Date of Birth of the User
    public void setDOB(int year){
        user.put("DOB", year);
    }
    //Tag Line? Interests.
    public void setTagLine(String tagLine){
        user.put("tagLine", tagLine);
    }

    public void setLanguageWantToLearn(Language language){ user.put("wantToLearn",language);}
    public void setLanguageKnows(Language language){user.put("knows", language);}

    //
    public void saveUser(Context context) {
        final Context c = context;
        user.signUpInBackground(new SignUpCallback() {

            @Override
            public void done(ParseException e) {

                if (e != null) {

                    Toast.makeText(c,
                            "Saving user failed.", Toast.LENGTH_SHORT).show();
                    Log.w("Saving User",
                            "Error : " + e.getMessage() + ":::" + e.getCode());
                } else {

                    Toast.makeText(c, "User Saved",
                            Toast.LENGTH_SHORT).show();

                    /*Do some things here if you want to.*/

                }

            }
        });
    }

    public void updateUser() {
        user.saveInBackground();
    }






}

