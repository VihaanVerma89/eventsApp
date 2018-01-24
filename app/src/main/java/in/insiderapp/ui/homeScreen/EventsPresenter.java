package in.insiderapp.ui.homeScreen;

import android.util.Log;

import in.insiderapp.data.EventsRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vihaanverma on 23/01/18.
 */

public class EventsPresenter implements EventsContract.Presenter {

    public static final String TAG = EventsPresenter.class.getSimpleName();
    private EventsRepository mEventsRepository;
    private CompositeDisposable mCompositeDisposable;
    private EventsContract.View mView;

    public EventsPresenter(EventsRepository eventsRepository, EventsContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mEventsRepository = eventsRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void getEvents(String norm, String filter, String city) {

        Disposable disposable = mEventsRepository.getEvents(norm, filter, city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(events -> {
                            Log.d(TAG, "getEvents: " + events);
                            mView.showEvents(events);
                        },
                        throwable -> {
                            Log.d(TAG, "getEvents: " + throwable.getMessage());
                            mView.showError(throwable);
                        });

        mCompositeDisposable.add(disposable);

    }

}
