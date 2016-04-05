package nl.bhogerheijde.example.rxmvp.network;

import nl.bhogerheijde.example.rxmvp.model.Flickr;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface FlickrApi {

    @GET(value = "services/rest/")
    Observable<Flickr> getRecentPhotos(@Query("method") String method,
                                       @Query("api_key") String apiKey,
                                       @Query("format") String format,
                                       @Query("nojsoncallback") String noJsonCallBack,
                                       @Query("per_page") Integer perPage,
                                       @Query("extras") String extras);

}
