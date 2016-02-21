package givebackhack2016.keytocommunication;

import android.os.Message;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class BuildConversation {

    public BuildConversation () {}

    public static ArrayList<Messages> getConversation(final User user) {
        final ArrayList<Messages> convo = new ArrayList<>();

        ParseQuery<Messages> query = ParseQuery.getQuery(Messages.class);
        query.whereEqualTo("sentTo", ParseUser.getCurrentUser());
        query.whereEqualTo("sentBy", user);
        query.addAscendingOrder("DateCreated");
        query.findInBackground(new FindCallback<Messages>() {
            public void done(final List<Messages> results, ParseException e) {
                if (e == null) {
                    Log.d("Conversation", "Retrieved messages");

                    ParseQuery<Messages> query = ParseQuery.getQuery(Messages.class);
                    query.whereEqualTo("sentTo", user);
                    query.whereEqualTo("sentBy", ParseUser.getCurrentUser());
                    query.addAscendingOrder("timeSent");
                    query.findInBackground(new FindCallback<Messages>() {
                        public void done(List<Messages> results2, ParseException e) {
                            if (e == null) {
                                Log.d("Conversation", "Retrieved messages");

                                for (Messages message : results) {
                                    if (!results2.isEmpty()) {
                                        if (message.getTimeSent().compareTo(results2.get(0).getTimeSent()) > 0) {
                                            convo.add(results2.remove(0));
                                        } else {
                                            convo.add(results.remove(0));
                                        }
                                    } else {
                                        convo.add(results.remove(0));
                                    }
                                }

                                if (!results2.isEmpty()) {
                                    for (Messages message : results2) {
                                        convo.add(results2.remove(0));
                                    }
                                }
                            }
                        }
                    });

                }
            }
        });
        return convo;
    }
}
