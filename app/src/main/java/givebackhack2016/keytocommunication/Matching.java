package givebackhack2016.keytocommunication;


import com.parse.ParseUser;

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
        int curUserAge = curUser.getDOB();
        int compUserAge = compUser.getDOB();
        boolean ages = ((curUserAge<18 && compUserAge<18)||(curUserAge>=18 && compUserAge>=18));

        return (languages && ages);
    }

}
