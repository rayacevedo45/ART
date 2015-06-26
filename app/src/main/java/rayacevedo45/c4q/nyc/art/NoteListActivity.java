package rayacevedo45.c4q.nyc.art;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;


public class NoteListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {

        return new NoteListFragment();
    }

    public void showCreateNote(View v) {
        Note note = new Note();
        note.setTitle("");
        NotePad.get(getApplicationContext()).addNote(note);
        Intent i = new Intent(getApplicationContext(), NotePagerActivity.class);
        i.putExtra(NoteFragment.EXTRA_NOTE_ID, note.getId());
        startActivityForResult(i, 0);
    }

}
