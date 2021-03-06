package in.insiderapp.ui.homeScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import in.insiderapp.R;
import in.insiderapp.network.models.Event;
import retrofit2.HttpException;

/**
 * Created by vihaanverma on 23/01/18.
 */

public class EventsFragment extends Fragment implements EventsContract.View,
        EventsAdapter.EventsListener {

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
        initProgressBar();
        initSwipeRefreshLayout();
        getEvents();
    }

    SwipeRefreshLayout mSwipeRefreshLayout;

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout = getView().findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.insider1),
                getResources().getColor(R.color .insider2));
        mSwipeRefreshLayout.setOnRefreshListener(() -> getEvents());
    }

    private ProgressBar mProgressBar;
    private void initProgressBar(){
        mProgressBar = getView().findViewById(R.id.progresBar);
    }

    private void getEvents() {
        //    https://api.insider.in/home?norm=1&filterBy=go-out&city=mumbai
        mPresenter.getEvents("1", "go-out", "mumbai");
    }

    private EventsContract.Presenter mPresenter;

    @Override
    public void setPresenter(EventsContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void onResume() {
        super.onResume();
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
    public void showEvents(List<Event> events) {
        mEvents = events;
        mEventsAdapter = new EventsAdapter(getActivity(), this, mEvents);
        mEventsRecyclerView.setAdapter(mEventsAdapter);
        mSwipeRefreshLayout.setRefreshing(false);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable throwable) {
        mProgressBar.setVisibility(View.GONE);
        if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (throwable instanceof UnknownHostException || throwable instanceof SocketException) {
            Toast.makeText(getActivity(), "Unable to reach network..", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onEventsClicked(int position) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }
}
