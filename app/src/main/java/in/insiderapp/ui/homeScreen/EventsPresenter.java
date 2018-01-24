package in.insiderapp.ui.homeScreen;

import android.util.EventLogTags;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import in.insiderapp.network.ApiClient;
import in.insiderapp.network.models.Event;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vihaanverma on 23/01/18.
 */

public class EventsPresenter implements EventsContract.Presenter {

    public static final String TAG = EventsPresenter.class.getSimpleName();

    private EventsContract.View mView;

    public EventsPresenter(EventsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    private List<String> getEventNames(JsonObject groupwiseList)
    {
        List<String> eventNames = new ArrayList<>();
        JsonArray events = groupwiseList.getAsJsonArray("Events");
        for(int i=0;i<events.size();i++)
        {
            eventNames.add(events.get(i).getAsString());
        }
        return eventNames;
    }

    @Override
    public Single<List<Event>> getEvents(String norm, String filter, String city) {

        //TODO
        // inject with dagger
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);

        Disposable subscribe = apiClient.getHomePage(norm, filter, city)
                .subscribeOn(Schedulers.io())
                .map(homePage -> {
                    List<String> eventNames = homePage.getList().getGroupwiseList().getEvents();
//                    List<String> eventNames = getEventNames(homePage.getList().getGroupwiseList());
                    JsonObject masterList = homePage.getList().getMasterList();

                    List<Event> events = new ArrayList<>();

                    Gson gson = new Gson();
                    for (String eventName : eventNames) {
                        JsonObject eventJson = masterList.getAsJsonObject(eventName);
                        Event event = gson.fromJson(eventJson.toString(), Event.class);
                        events.add(event);
                    }

                    return events;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(events -> {
                            Log.d(TAG, "getEvents: " + events);
                            mView.showEvents(events);
                        },
                        throwable -> {
                            Log.d(TAG, "getEvents: " + throwable.getMessage());
                            mView.showError(throwable);
                        });

        return null;
    }

}
