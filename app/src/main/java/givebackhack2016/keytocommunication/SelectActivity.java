package givebackhack2016.keytocommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseUser;

public class SelectActivity extends AppCompatActivity {
    private String wantLanguageTxt;
    private String knownLanguageTxt;
    //Drop Down Selection

    private String wantLangTxt;
    private String knownLangTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        setContentView(R.layout.activity_select);



        String[] langOptions = new String[]{"--Select--", "English", "Francais", "Espanol"};

        final Spinner knownLangDropdown = (Spinner)findViewById(R.id.knownlang);
        final ArrayAdapter<String> knownLangAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, langOptions);
        knownLangDropdown.setAdapter(knownLangAdapter);

        final Spinner wantLangDropdown = (Spinner)findViewById(R.id.wantlang);
        ArrayAdapter<String> wantLangAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, langOptions);
        wantLangDropdown.setAdapter(wantLangAdapter);

        Button submit = (Button) findViewById(R.id.submitlang);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Retrieve the text entered from the EditText
                wantLangTxt = wantLangDropdown.getSelectedItem().toString();
                knownLangTxt = knownLangDropdown.getSelectedItem().toString();


                // Force user to fill up the form
                if (wantLangTxt.equals("--Select--") || knownLangTxt.equals("--Select--") ) {
                    Toast.makeText(getApplicationContext(),
                            "Please select languages",
                            Toast.LENGTH_LONG).show();
                } else {

                    //Get user from the database and update
                    ParseUser parseUser = ParseUser.getCurrentUser();
                    User user = new User(parseUser);

                    //Create and set languages
                    Language wantedLanguage = new Language();
                    Language knownLanguage = new Language();

                    wantedLanguage.setLanguage(wantLangTxt);
                    knownLanguage.setLanguage(knownLangTxt);

                    user.setLanguageKnows(knownLanguage);
                    user.setLanguageWantToLearn(wantedLanguage);

                    //Save User?
                    user.updateUser();

                    Intent i = new Intent(SelectActivity.this, MainActivity.class);
                    startActivity(i);

                    finish();
                }
            }
        });
    }
}
