package givebackhack2016.keytocommunication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Wright on 2/20/16.
 */
public class UserListAdapter extends ArrayAdapter<User> {
    private final Context context;
    private User user;

    public UserListAdapter(Context context, int resource, List<User> userList) {
        super(context, resource, userList);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        user = getItem(position);

        View rowView = inflater.inflate(R.layout.activity_main, parent, false);

        TextView username = (TextView) rowView.findViewById(R.id.username);
        TextView tagline = (TextView) rowView.findViewById(R.id.tagline);
        TextView language = (TextView) rowView.findViewById(R.id.matchedlang);

        username.setText(user.getUsername());
        tagline.setText(user.getTagLine());
        String wantLang = user.getLanguageWantToLearn();
        String knownLang = user.getLanguageKnows();
        language.setText(wantLang);
        //set the language

        rowView.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               //Open Messages with new Chat
               Intent i  = new Intent(context, MessagesActivity.class);
               i.putExtra("id", user.getParseUser().getObjectId());
               //Open Messages with new Chat
           }
        });

        return rowView;
    }


}
