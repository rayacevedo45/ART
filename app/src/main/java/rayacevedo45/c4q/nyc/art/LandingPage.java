package rayacevedo45.c4q.nyc.art;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class LandingPage extends ActionBarActivity {
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);



        button = (Button) findViewById(R.id.ru);

        try {
            SharedPreferences settings = LandingPage.this.getSharedPreferences("PREFS_NAME", 0);
            settings = LandingPage.this.getSharedPreferences("PREFS_NAME", 0);
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

    public void newUser (View view){
        Intent intent = new Intent(LandingPage.this, MainActivity.class);

        startActivity(intent);
    }
    public void returningUser (View view){
        Intent intent = new Intent(LandingPage.this, Cards.class);
        //intent.putExtra("check","returningUser");
        startActivity(intent);
    }


}
