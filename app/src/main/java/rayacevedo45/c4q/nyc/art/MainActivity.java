package rayacevedo45.c4q.nyc.art;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private String name,birthdayS,zipcodeS;
    EditText firstName, birthDay, zipcode;
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

        SharedPreferences settings = MainActivity.this.getSharedPreferences("PREFS_NAME", 0);
        settings = MainActivity.this.getSharedPreferences("PREFS_NAME", 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("name", name);
        editor.putString("bday", birthdayS);
        editor.putString("zipcode", zipcodeS);
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
        startActivity(intent);
        }
        else {
            Toast.makeText(this,"Please double check inputs", Toast.LENGTH_LONG).show();
        }
    }
}
