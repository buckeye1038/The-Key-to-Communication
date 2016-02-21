package givebackhack2016.keytocommunication;
import java.io.Console;
import java.util.List;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            //noinspection SimplifiableIfStatement
            case R.id.action_sign_out:
                ParseUser.logOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_history:
                Intent intent2 = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent2);
                return true;
            default:
                // definitely shouldn't happen!
                return super.onOptionsItemSelected(item);
        }
    }
}
