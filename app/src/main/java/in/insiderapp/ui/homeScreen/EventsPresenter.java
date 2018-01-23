package in.insiderapp.ui.homeScreen;

import android.util.Log;

import java.sql.SQLInvalidAuthorizationSpecException;

import in.insiderapp.network.ApiClient;
import in.insiderapp.network.models.HomePage;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

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

    @Override
    public Single<HomePage> getEvents(String norm, String filter, String city) {

        //TODO
        // inject with dagger
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);

        Single<HomePage> homePage = apiClient.getHomePage(norm, filter, city);

        homePage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(events -> {
                            Log.d(TAG, "getEvents: "+ events);
                        },
                        throwable -> {
                            Log.d(TAG, "getEvents: "+ throwable.getMessage());
                        });

        return homePage;
    }

}
