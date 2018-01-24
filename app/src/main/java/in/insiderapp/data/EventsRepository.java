package in.insiderapp.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import in.insiderapp.network.ApiClient;
import in.insiderapp.network.models.Event;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vihaanverma on 25/01/18.
 */

public class EventsRepository implements EventsDataSource {

    private static EventsRepository INSTANCE = null;

    private EventsRepository(){
        initApiClient();
    }

    public static synchronized EventsRepository getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new EventsRepository();
        }
        return INSTANCE;
    }

    private ApiClient mApiClient;
    private void initApiClient(){

        //TODO
        // inject with dagger
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mApiClient = retrofit.create(ApiClient.class);
    }

    @Override
    public Single<List<Event>> getEvents(String norm, String filter, String city) {

        Single<List<Event>> eventsSingle = mApiClient.getHomePage(norm, filter, city)
                .map(homePage -> {
                    List<String> eventNames = homePage.getList().getGroupwiseList().getEvents();
                    JsonObject masterList = homePage.getList().getMasterList();

                    List<Event> events = new ArrayList<>();

                    Gson gson = new Gson();
                    for (String eventName : eventNames) {
                        JsonObject eventJson = masterList.getAsJsonObject(eventName);
                        Event event = gson.fromJson(eventJson.toString(), Event.class);
                        events.add(event);
                    }

                    return events;
                });
        return eventsSingle;
    }
}
