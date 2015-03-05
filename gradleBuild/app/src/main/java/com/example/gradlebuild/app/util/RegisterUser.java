package com.example.gradlebuild.app.util;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.example.gradlebuild.app.R;
import com.example.gradlebuild.app.util.util.JsonHttpHandler;
import com.example.gradlebuild.app.util.util.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RegisterUser extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void submitButton(View view){

        //initialize variables

        EditText firstNameBox = (EditText)findViewById(R.id.firstName);
        EditText lastNameBox = (EditText)findViewById(R.id.lastName);
        EditText passwdBox = (EditText)findViewById(R.id.passwd);
        EditText passwd2Box = (EditText)findViewById(R.id.passwd2);
        EditText emailBox = (EditText)findViewById(R.id.emailIn);

        String firstName = firstNameBox.getText().toString();
        String lastName = lastNameBox.getText().toString();
        String passwd = passwdBox.getText().toString();
        String passwd2 = passwdBox.getText().toString();
        String email = emailBox.getText().toString();


        User newUser = new User(firstName,lastName,email,passwd);



        //setup our http JSON handler
        final String JSONexample = "{\n" +
                "    \"glossary\": {\n" +
                "        \"title\": \"example glossary\",\n" +
                "\t\t\"GlossDiv\": {\n" +
                "            \"title\": \"S\",\n" +
                "\t\t\t\"GlossList\": {\n" +
                "                \"GlossEntry\": {\n" +
                "                    \"ID\": \"SGML\",\n" +
                "\t\t\t\t\t\"SortAs\": \"SGML\",\n" +
                "\t\t\t\t\t\"GlossTerm\": \"Standard Generalized Markup Language\",\n" +
                "\t\t\t\t\t\"Acronym\": \"SGML\",\n" +
                "\t\t\t\t\t\"Abbrev\": \"ISO 8879:1986\",\n" +
                "\t\t\t\t\t\"GlossDef\": {\n" +
                "                        \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\n" +
                "\t\t\t\t\t\t\"GlossSeeAlso\": [\"GML\", \"XML\"]\n" +
                "                    },\n" +
                "\t\t\t\t\t\"GlossSee\": \"markup\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";







        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JsonHttpHandler jsonhandler = new JsonHttpHandler();

                    JSONObject jsonuser = new JSONObject();
                    try {
                        jsonuser.put("Example",JSONexample);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonhandler.postJSONfromUrl("http://192.168.0.15:5000/new/user",jsonuser);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        t.start();


    }




}
