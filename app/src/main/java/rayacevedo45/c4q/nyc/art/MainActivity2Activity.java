package rayacevedo45.c4q.nyc.art;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity2Activity extends ActionBarActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        button = (Button) findViewById(R.id.ru);

        try {
            SharedPreferences settings = MainActivity2Activity.this.getSharedPreferences("PREFS_NAME", 0);
            settings = MainActivity2Activity.this.getSharedPreferences("PREFS_NAME", 0);
            String name = settings.getString("name", "");
            //button.setVisibility(View.VISIBLE);
            button.setText(name);
            if (button.getText().toString().equals("")){
                button.setVisibility(View.GONE);
            }

        }
        catch (Exception e){
            button.setVisibility(View.GONE);
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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
    public void newUser (View view){
        Intent intent = new Intent(MainActivity2Activity.this, MainActivity.class);

        startActivity(intent);
    }
    public void returningUser (View view){
        Intent intent = new Intent(MainActivity2Activity.this, Cards.class);
        //intent.putExtra("check","returningUser");
        startActivity(intent);
    }
}
