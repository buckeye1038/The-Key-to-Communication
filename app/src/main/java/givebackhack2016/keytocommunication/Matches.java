package givebackhack2016.keytocommunication;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by danielkrajnak on 2/20/16.
 */
public class Matches{
    private ArrayList<User> matchList;

    public void add(User x){
    this.matchList.add(x);
    }

    /**
     *
     * @param x The User to be removed
     * @requires matchList.contains(x)
     */
    public void remove(User x){
        this.matchList.remove(matchList.indexOf(x));
    }

    public void populateMatchList(){
        ParseQuery<User> userQuery = ParseQuery.getQuery(User.class);
        userQuery.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> objects, ParseException e) {
                if(e== null){
                    for(User user:objects){
                        if(givebackhack2016.keytocommunication.Matching.isMatch(user)){
                           matchList.add(user);
                        }
                    }
                    long seed= System.nanoTime();
                    Collections.shuffle(matchList, new Random(seed));
                    Collections.shuffle(matchList, new Random(seed));
                }
                else{
                    //HELP WHAT DO I DO IF THERE'S AN ERROR?
                }
            }
        });
    }
}