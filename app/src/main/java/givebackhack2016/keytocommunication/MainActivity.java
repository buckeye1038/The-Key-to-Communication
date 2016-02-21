package givebackhack2016.keytocommunication;
import java.util.List;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseUser currentParseUser = ParseUser.getCurrentUser();
        User currentUser = new User(currentParseUser);
        Matches matches = new Matches();
        Matching match = new Matching();
        String userKnownLanguage = currentUser.getLanguageKnows();
        String userWantToKnowLanguage = currentUser.getLanguageWantToLearn();

        //Get all of the user
        try {
            ParseQuery<User> allQuery = ParseQuery.getQuery(User.class);
            List<User> allUsers = new ArrayList<User>();

            allQuery.whereExists("userID");
            allUsers = allQuery.find();

            //Foreach of the users, compare. If compare is true, then add to matchesArr

            for(User user : allUsers){
                if(match.isMatch(user)){
                    matches.add(user);
                }
            }
        } catch (ParseException e) {

        }
        //Parse it from into cards

        // Display
        setContentView(R.layout.activity_main);




    }
}
