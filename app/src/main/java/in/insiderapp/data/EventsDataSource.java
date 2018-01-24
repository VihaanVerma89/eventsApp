package in.insiderapp.data;

import java.util.List;

import in.insiderapp.network.models.Event;
import io.reactivex.Single;

/**
 * Created by vihaanverma on 25/01/18.
 */

public interface EventsDataSource {
    Single<List<Event>> getEvents(String norm, String filter, String city);
}
