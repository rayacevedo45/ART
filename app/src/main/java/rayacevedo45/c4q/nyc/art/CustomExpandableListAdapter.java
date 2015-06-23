package rayacevedo45.c4q.nyc.art;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by c4q-Abass on 6/21/15.
 */
public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<Group> mGroups;
    private LayoutInflater mInflater;

    public CustomExpandableListAdapter(Context context, ArrayList<Group> groups) {
        mContext = context;
        mGroups = groups;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }



    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.card_group, null);
        }

        // Get the group item
        Group group = (Group) getGroup(groupPosition);

        // Set group name
//        TextView textView = (TextView) convertView.findViewById(R.id.textView1);
//        textView.setText(group.mName);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_cards, null);
        }

        // Get child name
        String children = (String) getChild(groupPosition, childPosition);

        // Set child name
//        TextView text = (TextView) convertView.findViewById(R.id.textView1);
//        text.setText(children);

        /*convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, children, Toast.LENGTH_SHORT).show();
            }
        });*/

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
