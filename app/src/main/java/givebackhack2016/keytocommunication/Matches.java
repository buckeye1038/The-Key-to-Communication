package givebackhack2016.keytocommunication;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Matches{
    private ArrayList<User> matchList;

    public void add(User x){
    this.matchList.add(x);
    }


    public void remove(User x){
        this.matchList.remove(matchList.indexOf(x));
    }

    public void populateMatchList(){
        ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
        userQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e== null){
                    for(ParseUser u : objects){
                        User user = new User(u);
                        if(givebackhack2016.keytocommunication.Matching.isMatch(user)){
                           matchList.add(user);
                        }
                    }
                    long seed= System.nanoTime();
                    Collections.shuffle(matchList, new Random(seed));
                    Collections.shuffle(matchList, new Random(seed));
                }
            }
        });
    }
}