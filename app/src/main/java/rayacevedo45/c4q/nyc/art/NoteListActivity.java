package rayacevedo45.c4q.nyc.art;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class NoteListActivity extends ActionBarActivity {
    private ArrayList<Note> mNotes;
    private NoteAdapter customadapter;
    private boolean mSubtitleVisible;
    private static final String TAG = "NoteListFragment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        mNotes = NotePad.get(getApplicationContext()).getNotes();   ///Testing this to see if it removes blank notes
        for (Note x : mNotes){
            if (  (x.getTitle().toString()).equals("")){
                mNotes.remove(x);
            }
        }




        final ListView listView = (ListView) findViewById(R.id.listview);
        customadapter = new NoteAdapter(mNotes);
        listView.setAdapter(customadapter);
        customadapter.setNotifyOnChange(true);
        customadapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note c = (Note) listView.getItemAtPosition(position);
                Log.d(TAG, c.getTitle() + " was clicked");

                Intent i = new Intent(getApplicationContext(), NotePagerActivity.class);
                i.putExtra(NoteFragment.EXTRA_NOTE_ID, c.getId());
                startActivity(i);
            }
        });
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_list_item_context, menu);
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                if (item.getItemId() == R.id.menu_item_delete_note) {

                    NotePad notePad = NotePad.get(getApplicationContext());
                    for (int i = customadapter.getCount() - 1; i >= 0; i--) {
                        if (listView.isItemChecked(i)) {
                            notePad.deleteNote(customadapter.getItem(i));
                        }
                    }
                    mode.finish();
                    customadapter.notifyDataSetChanged();
                    notePad.saveNotes();
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_list, menu);
        return true;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_note_list, menu);
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

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterViewCompat.AdapterContextMenuInfo info = (AdapterViewCompat.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        Note note = customadapter.getItem(position);

        switch (item.getItemId()) {
            case R.id.menu_item_delete_note:
                NotePad notePad = NotePad.get(getApplicationContext());
                notePad.deleteNote(note);
                customadapter.notifyDataSetChanged();
                notePad.saveNotes();
                return true;
        }

        return super.onContextItemSelected(item);
    }


    public void showCreateNote(View v) {
        Note note = new Note();
        note.setTitle("");
        NotePad.get(getApplicationContext()).addNote(note);
        Intent i = new Intent(getApplicationContext(), NotePagerActivity.class);
        i.putExtra(NoteFragment.EXTRA_NOTE_ID, note.getId());
        startActivityForResult(i, 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        customadapter.notifyDataSetChanged();
    }

    public class NoteAdapter extends ArrayAdapter<Note> {

        public int getCount() {
            return mNotes.size();
        }

        @Override
        public Note getItem(int position) {
            return mNotes.get(position);
        }


        public NoteAdapter(ArrayList<Note> notes) {
            super(getApplicationContext(), 0, notes);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_note, parent, false); //try list_item_note nxt
            }

            // Configure for this note
            Note c = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.note_list_title);
            if (c != null) {
                titleTextView.setText(c.getTitle());
            }


            TextView dateTextView = (TextView) convertView.findViewById(R.id.note_list_item_dateTV);

            if (c != null) {
                CharSequence cs = DateFormat.format("EEEE, MMM dd, yyyy", c.getDate());
                dateTextView.setText(cs);
            }


            CheckBox solvedCheckedBox = (CheckBox) convertView.findViewById(R.id.note_list_item_CheckBox);
            solvedCheckedBox.setChecked(c.isSolved());
            return convertView;
        }
    }




}
