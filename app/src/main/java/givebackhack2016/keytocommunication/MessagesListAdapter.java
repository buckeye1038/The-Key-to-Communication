package givebackhack2016.keytocommunication;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


class MessagesListAdapter extends ArrayAdapter<Messages> {

    private Messages item;
    private List<Messages> chatMessageList = new ArrayList<>();
    private Context context;

    public MessagesListAdapter(Context context, int resource, List<Messages> itemsList) {
        super(context, resource, itemsList);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


            // 1. Create inflater
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            item = getItem(position);
            View rowView;
            if (item.getSentBy().getParseUser().equals(ParseUser.getCurrentUser())) {
                rowView = inflater.inflate(R.layout.list_adapter_right, parent, false);
            }else{
                rowView = inflater.inflate(R.layout.list_adapter_left, parent, false);
            }


            // 3. Get the two text view from the rowView
            TextView title = (TextView) rowView.findViewById(R.id.msgr);


            // 4. Set the text for textView
            title.setText(item.getMessage());

        // 5. return rowView
        return rowView;
    }
}