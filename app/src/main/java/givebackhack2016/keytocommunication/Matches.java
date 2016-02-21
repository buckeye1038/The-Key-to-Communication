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
    public ArrayList<User> matchList;

    public void add(User x){
    this.matchList.add(x);
    }

    /**
     *
     * @param x The User to be removed
     * @requires matchList.contains(x)
     */
    public User remove(User x) {
        return this.matchList.remove(matchList.indexOf(x));
    }

    public User pop(){
        User user = null;
        if(!this.matchList.isEmpty()){
            user = this.matchList.remove(0);
        }
        return user;
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