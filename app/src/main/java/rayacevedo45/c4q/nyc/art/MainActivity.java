package rayacevedo45.c4q.nyc.art;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//To-do: Check for tablet devices, and create onclick to cycle through needed fragments.
public class MainActivity extends ActionBarActivity {
    private String name,birthdayS,zipcodeS,timeFormatS,degreeS;
    EditText firstName, birthDay, zipcode;
    TextView timeFormatTV, degreeTV;
    int monthInt, bdayInt;

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = (EditText) findViewById(R.id.firstNameET);
        birthDay = (EditText) findViewById(R.id.birthdayET);
        zipcode = (EditText) findViewById(R.id.zipcodeET);
        timeFormatTV = (TextView) findViewById(R.id.timeFormat);
        degreeTV = (TextView) findViewById(R.id.degreesID);
    }

    public void next (View v){
        if (!(firstName.getText().toString() .equals(""))){
            name = firstName.getText().toString();
        } else {
            name = "";
        }
        if (!(birthDay.getText().toString().equals(""))) {
            birthdayS = birthDay.getText().toString();
            String month = birthdayS.substring(0, 2);
            Log.d("month", month);
            String day = birthdayS.substring(3, 5);
            Log.d("day", day);
            monthInt = Integer.parseInt(month);
            bdayInt = Integer.parseInt(day);
        } else {
            monthInt = 0;
            bdayInt = 0;

        }
        if (!(zipcode.getText().toString().equals(""))) {
            zipcodeS = zipcode.getText().toString();
        } else{
            zipcodeS = "";
        }

        if (!(timeFormatTV.getText().equals("Time Format"))) {
            timeFormatS = timeFormatTV.getText().toString();
        } else{
            timeFormatS = "";
        }

        if (!(degreeTV.getText().equals("Would you like degrees in C of F?"))) {
            degreeS = degreeTV.getText().toString();
        } else{
            degreeS = "";
        }


        SharedPreferences settings = MainActivity.this.getSharedPreferences("PREFS_NAME", 0);
        settings = MainActivity.this.getSharedPreferences("PREFS_NAME", 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("name", name);
        editor.putString("bday", birthdayS);
        editor.putString("zipcode", zipcodeS);
        editor.putString("timeformat", timeFormatS);
        editor.putString("degree", degreeS);
        editor.commit();

        boolean zipValid;
        boolean monthValid;
        boolean bdayValid;
        boolean nameValid;

        zipValid = zipcodeS.length() == 5;
        monthValid = (monthInt > 0) && (monthInt < 13);
        bdayValid = (bdayInt > 0) && (bdayInt < 32);
        nameValid = (name.length() > 0);

        if (nameValid && zipValid && monthValid && bdayValid){
        Intent intent = new Intent(MainActivity.this, Cards.class);
        intent.putExtra("check","newUser");
        intent.putExtra("name",name);
        intent.putExtra("birthday", birthdayS);
        intent.putExtra("zipcode", zipcodeS);
        intent.putExtra("timeformat", timeFormatS);
        intent.putExtra("degree", degreeS);
        startActivity(intent);

//            JSONArray array;
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject item = array.getJSONObject(i);
//            }



        }
        else {
            Toast.makeText(this,"Please double check inputs", Toast.LENGTH_LONG).show();
        }
    }
    public void setTimeFormat12 (View v){
        timeFormatTV.setText("12hr time format");
    }
    public void setTimeFormat24 (View v){
        timeFormatTV.setText("24hr time format");
    }
    public void setDegreeC (View v){
        degreeTV.setText("C");
    }
    public void setDegreef (View v){
        degreeTV.setText("F");
    }

}
