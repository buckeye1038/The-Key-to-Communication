package givebackhack2016.keytocommunication;
import java.io.Console;
import java.util.List;
import java.util.ArrayList;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private Context mContext = MainActivity.this;
    private UserListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseUser currentParseUser = ParseUser.getCurrentUser();
        //User currentUser = new User(currentParseUser);
        Matches matches = new Matches();
        Matching match = new Matching();
        //String userKnownLanguage = currentUser.getLanguageKnows();
        //String userWantToKnowLanguage = currentUser.getLanguageWantToLearn();


        final ListView list = (ListView) findViewById(R.id.users_listview);
        //Get all of the user
        try {
            ParseQuery<ParseUser> allQuery = ParseQuery.getQuery(ParseUser.class);
            List<ParseUser> allUsers = new ArrayList<ParseUser>();

            allQuery.whereExists("userID");
            allUsers = allQuery.find();

            //Foreach of the users, compare. If compare is true, then add to matchesArr

            for(ParseUser parseUser : allUsers){
                User user = new User(parseUser);
                if(match.isMatch(user)){
                    matches.add(user);
                }
            }
        } catch (ParseException e) {

        }
        if(matches.matchList != null) {
            if (!matches.matchList.isEmpty()) {
                //Parse it from into card
                mAdapter = new UserListAdapter(mContext, R.layout.user_listview, matches.matchList);
                list.setAdapter(mAdapter);
            } else {
                //Print "There are no Users yet :("
            }
        }
            // Display





    }
}
