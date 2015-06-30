package rayacevedo45.c4q.nyc.art;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by c4q-Abass on 6/26/15.
 */
public class NotePagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList<Note> mNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewpager);
        setContentView(mViewPager);

        mNotes = NotePad.get(this).getNotes();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public int getCount() {
                return mNotes.size();
            }

            @Override
            public Fragment getItem(int p) {
                Note note = mNotes.get(p);
                return NoteFragment.newInstance(note.getId());
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int pos) {
                Note note = mNotes.get(pos);
                if (note.getTitle() != null) {
                    setTitle(note.getTitle());
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) { }

            @Override
            public void onPageScrollStateChanged(int arg0) { }
        });

        UUID noteId = (UUID)getIntent()
                .getSerializableExtra(NoteFragment.EXTRA_NOTE_ID);

        for (int i=0; i< mNotes.size(); i++)
        {
            if (mNotes.get(i).getId().equals(noteId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
