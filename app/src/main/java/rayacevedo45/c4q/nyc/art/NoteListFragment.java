package rayacevedo45.c4q.nyc.art;

import java.util.ArrayList;

import android.annotation.TargetApi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class NoteListFragment extends ListFragment {
    private ArrayList<Note> mNotes;
    private boolean mSubtitleVisible;
    private static final String TAG = "NoteListFragment";


    private Button createNoteButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.notes_title);
        mNotes = NotePad.get(getActivity()).getNotes();

        NoteAdapter adapter = new NoteAdapter(mNotes);
        setListAdapter(adapter);

        setRetainInstance(true);
        mSubtitleVisible = false;





    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note_list, container, false);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (mSubtitleVisible) {
                getActivity().getActionBar().setSubtitle(R.string.subtitle);
            }
//        }


        createNoteButton = (Button)v.findViewById(R.id.fragment_note_create);
        createNoteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateNote();
            }
        });

        ListView listView = (ListView)v.findViewById(android.R.id.list);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
        {
            // Use floating context menus on Froyo and Gingerbread
            registerForContextMenu(listView);
        } else {
            // Use contextual action bar on Honeycomb and higher
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) { }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.menu_list_item_context, menu);
                    return true;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_item_delete_note:
                            NoteAdapter adapter = (NoteAdapter)getListAdapter();
                            NotePad notePad = NotePad.get(getActivity());
                            for (int i=adapter.getCount() -1; i >=0; i--) {
                                if (getListView().isItemChecked(i)) {
                                    notePad.deleteNote(adapter.getItem(i));
                                }
                            }
                            mode.finish();
                            adapter.notifyDataSetChanged();
                            notePad.saveNotes();
                            return true;
                        default:
                            return false;
                    }

                }

                @Override
                public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                      long id, boolean checked) { }
            });
        }

        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Note c = ((NoteAdapter)getListAdapter()).getItem(position);
		Log.d(TAG, c.getTitle() + " was clicked");


        Intent i = new Intent(getActivity(), NotePagerActivity.class);
        i.putExtra(NoteFragment.EXTRA_NOTE_ID, c.getId());
        startActivity(i);
    }

    public void showCreateNote() {
        Note note = new Note();
        NotePad.get(getActivity()).addNote(note);
        Intent i = new Intent(getActivity(), NotePagerActivity.class);
        i.putExtra(NoteFragment.EXTRA_NOTE_ID, note.getId());
        startActivityForResult(i, 0);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
        int position = info.position;
        NoteAdapter adapter = (NoteAdapter)getListAdapter();
        Note note = adapter.getItem(position);

        switch (item.getItemId()) {
            case R.id.menu_item_delete_note:
                NotePad notePad = NotePad.get(getActivity());
                notePad.deleteNote(note);
                adapter.notifyDataSetChanged();
                notePad.saveNotes();
                return true;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((NoteAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_note_list, menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.menu_note_list, menu);
    }



    private class NoteAdapter extends ArrayAdapter<Note> {

        public void showCreateNote() {
            Note note = new Note();
            NotePad.get(getActivity()).addNote(note);
            Intent i = new Intent(getActivity(), NotePagerActivity.class);
            i.putExtra(NoteFragment.EXTRA_NOTE_ID, note.getId());
            startActivityForResult(i, 0);
        }



        public NoteAdapter(ArrayList<Note> notes) {
            super(getActivity(), 0, notes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_note, parent, false); //try list_item_note nxt
            }


            // Configure for this note
            Note c = getItem(position);

            TextView titleTextView = (TextView)convertView.findViewById(R.id.note_list_title);
            if (c != null) {
                titleTextView.setText(c.getTitle());
            }



            TextView dateTextView = (TextView)convertView.findViewById(R.id.note_list_item_dateTV);

            if (c != null) {
                CharSequence cs = DateFormat.format("EEEE, MMM dd, yyyy", c.getDate());
                dateTextView.setText(cs);
            }


            CheckBox solvedCheckedBox = (CheckBox)convertView.findViewById(R.id.note_list_item_CheckBox);
            solvedCheckedBox.setChecked(c.isSolved());

            return convertView;
        }
    }


}
