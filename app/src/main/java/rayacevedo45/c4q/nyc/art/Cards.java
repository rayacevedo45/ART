package rayacevedo45.c4q.nyc.art;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

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

        //can set conditions that this loads the screen for
        // creating a new note or it shows the list depending on current content.
        Button NoteTest = (Button) findViewById(R.id.openNoteButton);
        NoteTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cards.this, NoteListActivity.class);
                startActivity(intent);
            }
        });

    }


    public void initializeViewsAndValues(){

        welcome = (TextView) findViewById(R.id.welcomeTV);
        horoscopeCV = (CardView) findViewById(R.id.card_view2);
        horoscopeTV = (TextView) findViewById(R.id.horoscopeTVID);
        top = (LinearLayout) findViewById(R.id.calenederLL);
        imageView = (ImageView) findViewById(R.id.weatherIV);
        todotv = (TextView) findViewById(R.id.ToDoList);
        //cv = (CalendarView) findViewById(R.id.cv);


        SharedPreferences settings = Cards.this.getSharedPreferences("PREFS_NAME", 0);
        settings = Cards.this.getSharedPreferences("PREFS_NAME", 0);
        name = settings.getString("name", "");
        birthdayS = settings.getString("bday", "");
        zipcodeS = settings.getString("zipcode", "");


         Bundle extras = getIntent().getExtras();

        welcome.setText("Hello, " + name);

        userSign = findUserSign();

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


    public String findUserSign() {
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
        return userSign;
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

