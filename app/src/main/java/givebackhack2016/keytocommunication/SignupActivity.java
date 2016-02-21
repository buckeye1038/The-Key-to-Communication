package givebackhack2016.keytocommunication;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.Date;

public class SignupActivity extends AppCompatActivity {
    //Declare variables
    private String userNameTxt;
    private String firstNameTxt;
    private String lastNameTxt;
    private int dobTxt;
    private String passwordTxt;
    private String tagLineTxt;
    private EditText userName;
    private EditText firstName;
    private EditText lastName;
    private EditText dob;
    private EditText password;
    private EditText tagLine;
    private Button submit;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Locate EditTexts in main.xml
        userName = (EditText) findViewById(R.id.username);
        firstName = (EditText) findViewById(R.id.firstname);
        lastName = (EditText) findViewById(R.id.lastname);
        dob = (EditText) findViewById(R.id.dateOfBirth);
        password = (EditText) findViewById(R.id.password);
        tagLine = (EditText) findViewById(R.id.tagLine);
        // Locate Buttons in main.xml
        submit = (Button) findViewById(R.id.submit);

        // Sign up Button Click Listener
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Retrieve the text entered from the EditText
                userNameTxt = userName.getText().toString();
                passwordTxt = password.getText().toString();
                firstNameTxt = firstName.getText().toString();
                lastNameTxt = lastName.getText().toString();
                dobTxt = Integer.parseInt(dob.getText().toString());
                tagLineTxt = tagLine.getText().toString();

                // Force user to fill up the form
                if (userNameTxt.equals("") || passwordTxt.equals("") || firstNameTxt.equals("") || lastNameTxt.equals("") || dobTxt == 0 || tagLineTxt.equals("") ){
                    Toast.makeText(getApplicationContext(),
                            "Please complete the sign up form",
                            Toast.LENGTH_LONG).show();
                } else {

                    // Save new user data into Parse.com Data Storage
//                    ParseUser parseUser = new ParseUser();
//                    parseUser.setUsername(userNameTxt);
//                    parseUser.setPassword(passwordTxt);

                    User user = new User();
                    user.setUsername(userNameTxt);
                    user.setPassword(passwordTxt);
                    user.setFirstName(firstNameTxt);
                    user.setLastName(lastNameTxt);
                    user.setDOB(dobTxt);
                    user.setTagLine(tagLineTxt);
                    user.saveUser(getApplicationContext());
                    finish();
                }
            }
        });
    }
}
