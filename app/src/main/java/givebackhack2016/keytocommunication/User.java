package givebackhack2016.keytocommunication;

import android.content.Context;
import android.widget.Toast;

import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.SignUpCallback;
import java.util.Date;

/**
 * Created by Wright on 2/20/16.
 */
public class User extends ParseUser {

    private ParseUser user;
    //Constructors
    public User() {
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

    public Date getDOB(){
        return user.getDate("DOB");
    }

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
        user.put("firstName", lName);
    }

    //Date of Birth of the User
    public void setDOB(Date date){
        user.put("DOB", date);
    }
    //Tag Line? Interests.
    public void setTagLine(String tagLine){
        user.put("tagLine", tagLine);
    }

    public void saveUser(Context context) {
        final Context c = context;
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Show a simple Toast message upon successful registration
                    Toast.makeText(c,
                            "Successfully Signed up, please log in.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(c,
                            "Sign up Error", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    public void updateUser() {
        user.saveInBackground();
    }






}

