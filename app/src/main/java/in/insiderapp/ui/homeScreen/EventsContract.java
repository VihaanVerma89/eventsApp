package in.insiderapp.ui.homeScreen;

import java.util.List;

import in.insiderapp.network.models.Event;
import in.insiderapp.network.models.HomePage;
import in.insiderapp.ui.BasePresenter;
import in.insiderapp.ui.BaseView;
import io.reactivex.Single;

/**
 * Created by vihaanverma on 23/01/18.
 */

public class EventsContract {
    interface View extends BaseView<Presenter> {

        void showEvents(List<Event> events);
    }

    interface Presenter extends BasePresenter {

        Single<HomePage> getEvents(String norm, String filter, String city);
    }
}
