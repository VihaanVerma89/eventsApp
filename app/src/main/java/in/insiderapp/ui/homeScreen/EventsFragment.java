package in.insiderapp.ui.homeScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.EventLogTags;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import in.insiderapp.R;
import in.insiderapp.network.models.Event;

/**
 * Created by vihaanverma on 23/01/18.
 */

public class EventsFragment extends Fragment implements EventsContract.View ,
        EventsAdapter.EventsListener{

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        initRecyclerView();
    }

    private EventsContract.Presenter mPresenter;

    @Override
    public void setPresenter(EventsContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void onResume() {
        super.onResume();
        //    https://api.insider.in/home?norm=1&filterBy=go-out&city=mumbai
        mPresenter.getEvents("1", "go-out", "mumbai");
    }

    private RecyclerView mEventsRecyclerView;
    private EventsAdapter mEventsAdapter;
    private List<Event> mEvents;
    private void initRecyclerView() {
        mEventsRecyclerView = getView().findViewById(R.id.eventsRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mEventsRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void showEvents(List<Event> events)
    {
        mEvents = events;
        mEventsAdapter = new EventsAdapter(getActivity(), this, mEvents);
        mEventsRecyclerView.setAdapter(mEventsAdapter);
    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void onEventsClicked(int position) {

    }
}
