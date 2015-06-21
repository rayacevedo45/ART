package rayacevedo45.c4q.nyc.art;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {
    private String name,birthDayS,zipcodeS;
    EditText firstName, birthDay, zipcode;


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
        birthDayS = birthDay.getText().toString();
        zipcodeS = zipcode.getText().toString();
        Intent intent = new Intent(MainActivity.this, Cards.class);
        intent.putExtra("name",name);
        intent.putExtra("birthday", birthDayS);
        intent.putExtra("zipcode", zipcodeS);
        startActivity(intent);
    }
}
