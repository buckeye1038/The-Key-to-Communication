package givebackhack2016.keytocommunication;


public class Send {
    public Send(){
        //Default constructor
    }
    public static void sendMessage(Messages message){
        message.saveInBackground();
    }
}
