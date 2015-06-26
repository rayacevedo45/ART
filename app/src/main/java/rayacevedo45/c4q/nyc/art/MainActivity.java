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
        name = firstName.getText().toString();
        birthdayS = birthDay.getText().toString();
        zipcodeS = zipcode.getText().toString();
        String month = birthdayS.substring(0, 2);
        Log.d("month", month);
        String day = birthdayS.substring(3, 5);
        Log.d("day", day);
        int monthInt = Integer.parseInt(month);
        int bdayInt = Integer.parseInt(day);


        SharedPreferences settings = MainActivity.this.getSharedPreferences("PREFS_NAME", 0);
        settings = MainActivity.this.getSharedPreferences("PREFS_NAME", 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("name", name);
        editor.putString("bday", birthdayS);
        editor.putString("zipcode", zipcodeS);
        editor.commit();

        if (zipcodeS.length() == 5 && monthInt < 13 && bdayInt < 32){
        Intent intent = new Intent(MainActivity.this, Cards.class);
//        intent.putExtra("check","newUser");
//        intent.putExtra("name",name);
//        intent.putExtra("birthday", birthDayS);
//        intent.putExtra("zipcode", zipcodeS);
        startActivity(intent);
        }
        else {
            Toast.makeText(this,"Please double check inputs", Toast.LENGTH_LONG).show();
        }
    }
}
