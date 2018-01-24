package in.insiderapp.ui.homeScreen;

import java.util.List;

import in.insiderapp.network.models.Event;
import in.insiderapp.ui.BasePresenter;
import in.insiderapp.ui.BaseView;

/**
 * Created by vihaanverma on 23/01/18.
 */

public class EventsContract {
    interface View extends BaseView<Presenter> {

        void showEvents(List<Event> events);

        void showError(Throwable throwable);
    }

    interface Presenter extends BasePresenter {

        void getEvents(String norm, String filter, String city);
    }
}
