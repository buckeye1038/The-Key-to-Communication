package givebackhack2016.keytocommunication;

/**
 * Created by danielkrajnak on 2/20/16.
 */
public class Send {
    public Send(){
        //Default constructor

    }
    public void send(Messages message){
        message.saveInBackground();
    }
}
