package in.insiderapp.ui.homeScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.insiderapp.R;

/**
 * Created by vihaanverma on 23/01/18.
 */

public class EventsFragment extends Fragment {

    public static EventsFragment newInstance() {

        Bundle args = new Bundle();

        EventsFragment fragment = new EventsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        return view;
    }


}
