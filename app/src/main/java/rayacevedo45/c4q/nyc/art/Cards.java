package rayacevedo45.c4q.nyc.art;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Cards extends ActionBarActivity {
    TextView welcome,horoscopeTV, date, time, amPm, location, temperature;
    private String name,birthdayS,zipcodeS,userSign, dateStr, amPmStr, locationStr, temperatureStr, seconds;
    Calendar rightNow;
import java.util.ArrayList;
import java.util.HashMap;


public class Cards extends ActionBarActivity {

    TextView welcome,horoscopeTV, todotv;
    private String name,birthdayS,zipcodeS,userSign;
    private JSONParser parser;
    private JSONObject dailyHoroscopeObject;
    private String dailyHoroscopeString;
    CardView horoscopeCV;
    LinearLayout top;
    ImageView imageView;
    CalendarView cv;

//    public static final String MyPREFERENCES = "MyPrefs" ;
//    SharedPreferences sharedpreferences;



    public static final String[] CARDS = {"To-Do List", "Horoscope", "Weather", "Stocks"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        parser = new JSONParser();
        AsyncTime getDailyHoroscope = new AsyncTime();
        getDailyHoroscope.execute();
        initializeViewsAndValues();




    }


    public void initializeViewsAndValues(){

        welcome = (TextView) findViewById(R.id.welcomeTV);
        horoscopeCV = (CardView) findViewById(R.id.card_view2);
        horoscopeTV = (TextView) findViewById(R.id.horoscopeTVID);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
        amPm = (TextView) findViewById(R.id.am_pm);
        location = (TextView) findViewById(R.id.location);
        temperature = (TextView) findViewById(R.id.temperature);

        //get day of week and display in textView
        rightNow = Calendar.getInstance();
        int dayofweek = rightNow.get(Calendar.DAY_OF_WEEK);

        if (dayofweek == 1) {
            date.setText("Sun, ");
        }
        else if (dayofweek == 2) {
            date.setText("Mon, ");
        }
        else if (dayofweek == 3) {
            date.setText("Tues, ");
        }
        else if (dayofweek == 4) {
            date.setText("Wed, ");
        }
        else if (dayofweek == 5) {
            date.setText("Thurs, ");
        }
        else if (dayofweek == 6) {
            date.setText("Fri, ");
        }
        else {
            date.setText("Sat, ");
        }

        //get date and display to TextView
        int month = rightNow.get(Calendar.MONTH);
        if (month == 0) {
            dateStr = "January ";
        }
        else if (month == 1) {
            dateStr = "February ";
        }
        else if (month == 2) {
            dateStr = "March ";
        }
        else if (month == 3) {
            dateStr = "April ";
        }
        else if (month == 4) {
            dateStr = "May ";
        }
        else if (month == 5) {
            dateStr = "June ";
        }
        else if (month == 6) {
            dateStr = "July ";
        }
        else if (month == 7) {
            dateStr = "August ";
        }
        else if (month == 8) {
            dateStr = "September ";
        }
        else if (month == 9) {
            dateStr = "October ";
        }
        else if (month == 10) {
            dateStr = "November ";
        }
        else {
            dateStr = "December ";
        }
        top = (LinearLayout) findViewById(R.id.calenederLL);
        imageView = (ImageView) findViewById(R.id.weatherIV);
        todotv = (TextView) findViewById(R.id.ToDoList);
        cv = (CalendarView) findViewById(R.id.cv);


        SharedPreferences settings = Cards.this.getSharedPreferences("PREFS_NAME", 0);
        settings = Cards.this.getSharedPreferences("PREFS_NAME", 0);
        name = settings.getString("name", "");
        birthdayS = settings.getString("bday", "");
        zipcodeS = settings.getString("zipcode", "");


         Bundle extras = getIntent().getExtras();

        welcome.setText("Hello, " + name);

        findUserSign();

        horoscopeCV.setOnTouchListener(new OnSwipeTouchListener(Cards.this) {
            @Override
            public void onSwipeLeft() {
                horoscopeCV.setVisibility(View.GONE);
            }
        });
        top.setOnTouchListener(new OnSwipeTouchListener(Cards.this) {
            @Override
            public void onSwipeLeft() {
                top.setVisibility(View.GONE);
            }
        });
        imageView.setOnTouchListener(new OnSwipeTouchListener(Cards.this) {
            @Override
            public void onSwipeLeft() {
                imageView.setVisibility(View.GONE);
            }
        });



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


    public void findUserSign() {
        String month = birthdayS.toString().substring(0, 2);
        //Log.d("{{{",month);

        Log.d("month", month);
        String day = birthdayS.toString().substring(3, 5);
        Log.d("day", day);
        int monthInt = Integer.parseInt(month);
        int bdayInt = Integer.parseInt(day);

        if (monthInt == 1 && bdayInt >= 21) {
            userSign = "aquarius";
        } else if (monthInt == 2 && bdayInt <= 19) {
            userSign = "aquarius";
        } else if (monthInt == 2 && bdayInt >= 21) {
            userSign = "pisces";
        } else if (monthInt == 3 && bdayInt <= 19) {
            userSign = "pisces";
        } else if (monthInt == 3 && bdayInt >= 21) {
            userSign = "aries";
        } else if (monthInt == 4 && bdayInt <= 19) {
            userSign = "aries";
        } else if (monthInt == 4 && bdayInt >= 20) {
            userSign = "taurus";
        } else if (monthInt == 5 && bdayInt <= 20) {
            userSign = "taurus";
        } else if (monthInt == 5 && bdayInt >= 21) {
            userSign = "gemini";
        } else if (monthInt == 6 && bdayInt <= 21) {
            userSign = "gemini";
        } else if (monthInt == 6 && bdayInt >= 22) {
            userSign = "cancer";
        } else if (monthInt == 7 && bdayInt <= 22) {
            userSign = "cancer";
        } else if (monthInt == 7 && bdayInt >= 23) {
            userSign = "leo";
        } else if (monthInt == 8 && bdayInt <= 22) {
            userSign = "leo";
        } else if (monthInt == 8 && bdayInt >= 23) {
            userSign = "virgo";
        } else if (monthInt == 9 && bdayInt <= 22) {
            userSign = "virgo";
        } else if (monthInt == 9 && bdayInt >= 23) {
            userSign = "libra";
        } else if (monthInt == 10 && bdayInt <= 22) {
            userSign = "libra";
        } else if (monthInt == 10 && bdayInt >= 23) {
            userSign = "scorpio";
        } else if (monthInt == 11 && bdayInt <= 21) {
            userSign = "scorpio";
        } else if (monthInt == 11 && bdayInt >= 22) {
            userSign = "sagittarius";
        } else if (monthInt == 12 && bdayInt <= 19) {
            userSign = "sagittarius";

        } else {
            userSign = "capricorn";
        }
    }






    public class AsyncTime extends AsyncTask<Void, Void, HashMap> {

    //public class AsyncTime extends AsyncTask<Void, Void, String> {

        @Override
        public HashMap doInBackground(Void... voids) {
            String horoscopeAPISite = "http://widgets.fabulously40.com/horoscope.json?sign=" + userSign;
            dailyHoroscopeObject = parser.parse(horoscopeAPISite);

            HashMap JSONresults = new HashMap();
//            dailyStockObject = parser.parse(stockAPISite);
//            dailyWeatherObject = parser.parse(weatherAPIObject);
            try {
                JSONObject dailyHoroscope = dailyHoroscopeObject.getJSONObject("horoscope");
                dailyHoroscopeString = dailyHoroscope.getString("horoscope");
            } catch (Exception e ){

            }
            JSONresults.put("horoscopeString", dailyHoroscopeString);
            return JSONresults;
        }

        @Override
        public void onPostExecute(HashMap s) {
                horoscopeTV.setText(userSign +" daily horoscope \n" + s.get("horoscopeString"));
        }


    }





    public void setUpStockCard(){

    }
    public class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureListener());
        }

        public void onSwipeLeft() {
        }

        public void onSwipeRight() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_DISTANCE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();
                if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0)
                        onSwipeRight();
                    else
                        onSwipeLeft();
                    return true;
                }
                return false;
            }
        }
    }
}

