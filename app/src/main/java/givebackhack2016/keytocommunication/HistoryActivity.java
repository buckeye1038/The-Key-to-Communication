package givebackhack2016.keytocommunication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private Context mContext = HistoryActivity.this;
    private UserListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ParseUser currentParseUser = ParseUser.getCurrentUser();
        User currentUser = new User(currentParseUser);
        Matches matches = new Matches();
        Matching match = new Matching();
        //String userKnownLanguage = currentUser.getLanguageKnows();
        //String userWantToKnowLanguage = currentUser.getLanguageWantToLearn();


        final ListView list = (ListView) findViewById(R.id.users_listview);
        List<User> usersWithConvos = new ArrayList<User>();
        //Get all of the user
        try {

            ParseQuery<Messages> convoQuery = ParseQuery.getQuery(Messages.class);
            List<Messages> allConvos = new ArrayList<Messages>();

            convoQuery.whereContains("sentTo", currentUser.getUserID());
            allConvos = convoQuery.find();

            ParseQuery<ParseUser> userQuery = ParseQuery.getQuery(ParseUser.class);


            while(!allConvos.isEmpty()){
                Messages message = allConvos.remove(0);
                User user = message.getSentBy();
                usersWithConvos.add(user);
            }

        } catch (ParseException e) {

        }

        if(!usersWithConvos.isEmpty()) {
            mAdapter = new UserListAdapter(mContext, R.layout.user_listview, matches.matchList);
            list.setAdapter(mAdapter);
        } else {
            //Print "You haven't had convos yet :("
        }
    }
}
