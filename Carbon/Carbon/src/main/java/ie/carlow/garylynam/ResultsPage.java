package ie.carlow.garylynam;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsPage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();
        String RPM = intent.getStringExtra("Var_RPM");
        String SPEED = intent.getStringExtra("Var_SPEED");
        String FUEL = intent.getStringExtra("Var_Fuel");
        final TextView textViewToChange = (TextView) findViewById(R.id.rpm);
        textViewToChange.setText(RPM);
        final TextView textView = (TextView) findViewById(R.id.spd);
        textView.setText(SPEED);
        final TextView textView1 = (TextView) findViewById(R.id.fl);
        textView1.setText(FUEL);
    }


}
