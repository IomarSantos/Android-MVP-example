package nl.bhogerheijde.example.rxmvp.service;

import nl.bhogerheijde.example.rxmvp.model.Flickr;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Flickr app built with RxJava and MVP pattern, file created on 26/03/16.
 *
 * @author Boyd Hogerheijde
 */
public class FlickrService {

    private static final String API_BASE_URL = "https://api.flickr.com/";

    private static FlickrService flickrService;

    private FlickrApi flickrApi;

    private FlickrService() {
        flickrApi = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FlickrApi.class);
    }

    public static FlickrService getInstance() {
        if (flickrService == null) flickrService = new FlickrService();
        return flickrService;
    }

    public FlickrApi getFlickrApi() {
        return flickrApi;
    }

    public interface FlickrApi {

        @GET(value = "services/rest/")
        Observable<Flickr> getRecentPhotos(@Query("method") String method,
                                           @Query("api_key") String apiKey,
                                           @Query("format") String format,
                                           @Query("nojsoncallback") String noJsonCallBack,
                                           @Query("extras") String extras);

    }

}
