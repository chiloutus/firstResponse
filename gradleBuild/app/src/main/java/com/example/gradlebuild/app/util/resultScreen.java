package com.example.gradlebuild.app.util;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.gradlebuild.app.R;

public class resultScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();
        String RPM = intent.getStringExtra("Var_RPM");
        String SPEED = intent.getStringExtra("Var_Speed");
        String FUEL = intent.getStringExtra("Var_Fuel");
        final TextView textViewToChange = (TextView) findViewById(R.id.rpm);
        textViewToChange.setText(RPM);
        final TextView textView = (TextView) findViewById(R.id.spd);
        textView.setText(SPEED);
        final TextView textView1 = (TextView) findViewById(R.id.fl);
        textView1.setText(FUEL);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.result_screen, menu);
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
}
