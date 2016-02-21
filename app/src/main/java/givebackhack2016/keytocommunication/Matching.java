package givebackhack2016.keytocommunication;


import com.parse.ParseUser;
import java.util.Calendar;

/**
 * Created by danielkrajnak on 2/20/16.
 */
public class Matching {
    public Matching(){
        //default constructor
    }



    public static boolean isMatch(User compUser){
        User curUser = new User(ParseUser.getCurrentUser());
        boolean languages = (compUser.getLanguageWantToLearn().equals(curUser.getLanguageKnows()) &&
        compUser.getLanguageKnows().equals(curUser.getLanguageWantToLearn()));
        Calendar rightNow = Calendar.getInstance();
        int curUserAge = rightNow.YEAR-curUser.getDOB();
        int compUserAge = rightNow.YEAR-compUser.getDOB();
        boolean ages = ((curUserAge<18 && compUserAge<18)||(curUserAge>=18 && compUserAge>=18));

        return (languages && ages);
    }

}
