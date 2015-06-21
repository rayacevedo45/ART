package rayacevedo45.c4q.nyc.art;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Cards extends ActionBarActivity {
    TextView welcome;
    private String name,birthdayS,zipcodeS;

    public static final String[] CARDS = {"To-Do List", "Horoscope", "Weather", "Stocks"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        birthdayS = extras.getString("birthday");
        zipcodeS = extras.getString("zipcode");

        welcome = (TextView) findViewById(R.id.welcomeTV);
        welcome.setText("Hello, " + name);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cards, menu);
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





}
