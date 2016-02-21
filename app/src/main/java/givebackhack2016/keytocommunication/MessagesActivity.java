package givebackhack2016.keytocommunication;


import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;


public class MessagesActivity extends Activity {
    private static final String TAG = "ChatActivity";
    private ArrayList<Messages> conversation;
    private MessagesListAdapter messagesListAdapter;
    private ListView listView;
    private EditText chatText;
    private Button buttonSend;
    private Button toggleBtn;
    private User user;
    private User currentUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        String userID = i.getStringExtra("id");

        currentUser = new User(ParseUser.getCurrentUser());

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.getInBackground(userID, new GetCallback<ParseUser>() {
            public void done(ParseUser u, ParseException e) {
                if (e == null) {
                    user = new User(u);
                }
            }
        });

        conversation = BuildConversation.getConversation(user);
        buttonSend = (Button) findViewById(R.id.send);
        listView = (ListView) findViewById(R.id.msgview);
        listView = (ListView) findViewById(R.id.msgview);
        chatText = (EditText) findViewById(R.id.msg);
        toggleBtn = (Button) findViewById(R.id.tog);

        toggleBtn.setText(currentUser.getLanguageKnows());

        messagesListAdapter = new MessagesListAdapter(getApplicationContext(), R.layout.list_adapter_right, conversation);
        listView.setAdapter(messagesListAdapter);

        setContentView(R.layout.activity_messages);

        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            }
        });

        //Send Message
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });

        //Change Language
        toggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                toggleBtn.setText(user.getLanguageKnows());
            }
        });

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(messagesListAdapter);

        //to scroll the list view to bottom on data change
        messagesListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(messagesListAdapter.getCount() - 1);
            }
        });
    }

    private boolean sendChatMessage() {

        Messages msg = new Messages();
        msg.setLanguage(new Language(toggleBtn.getText().toString()));
        msg.setTimeSent(Calendar.getInstance());
        msg.setMessage(chatText.getText().toString());
        msg.setSentTo(user);
        msg.setSentyBy(currentUser);
        chatText.setText("");
        Send.sendMessage(msg);
        return true;

    }
}