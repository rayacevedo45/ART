package rayacevedo45.c4q.nyc.art;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class horoscopeFragment extends Fragment {
    CardView cardView;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horoscope, container, false);

        cardView = (CardView) view.findViewById(R.id.card_view2);
        textView = (TextView) view.findViewById(R.id.horoscopeTVID);


        // Inflate the layout for this fragment
        return view;
    }


}
