package in.insiderapp.network;

import in.insiderapp.network.models.HomePage;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vihaanverma on 23/01/18.
 */

public interface ApiClient {

    public static final String BASE_URL = "https://api.insider.in/";

//    https://api.insider.in/home?norm=1&filterBy=go-out&city=mumbai
    @GET("/home")
    Single<HomePage> getHomePage(@Query("norm") String norm, @Query("filterBy") String filter,
                                 @Query("city") String city);
}
