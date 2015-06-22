package rayacevedo45.c4q.nyc.art;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Cards extends ActionBarActivity {
    TextView welcome;
    private String name,birthdayS,zipcodeS;

    public static final String[] CARDS = {"To-Do List", "Horoscope", "Weather", "Stocks"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listview);

        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        birthdayS = extras.getString("birthday");
        zipcodeS = extras.getString("zipcode");

        welcome = (TextView) findViewById(R.id.welcomeTV);
        welcome.setText("Hello, " + name);

        ArrayList<Group> groups = prepareData();
        final CustomExpandableListAdapter adapter = new CustomExpandableListAdapter(this, groups);
        ListView listview = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Group group = (Group) adapter.getGroup(groupPosition);

                Toast
                        .makeText(getApplicationContext(), group.mName, Toast.LENGTH_SHORT)
                        .show();

                return false;
            }
        });

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String child = (String) adapter.getChild(groupPosition, childPosition);

                Toast
                        .makeText(getApplicationContext(), child, Toast.LENGTH_SHORT)
                        .show();

                return false;
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

    public ArrayList<Group> prepareData() {

        Group group1 = new Group("Weather");
        group1.children.add("SAMPLE");
        group1.children.add("test");

        Group group2 = new Group("Julio Boes");
        group2.children.add("wr=sss");
        group2.children.add("xxxxxxx");

        Group group3 = new Group("Ron Osmun");
        group3.children.add("ron.osmun@gmail.com");
        group3.children.add("osmun.ron@gmail.com");

        Group group4 = new Group("Angelica Tebbs");
        group4.children.add("angelica.tebbs@gmail.com");
        group4.children.add("tebbs.angelica@gmail.com");

        ArrayList<Group> groups = new ArrayList<Group>();
        groups.add(group1);
        groups.add(group2);
        groups.add(group3);
        groups.add(group4);

        return groups;
    }







}
