package nl.bhogerheijde.example.rxmvp.api;

import android.app.Application;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Module(
        complete = false,
        library = true
)
public class NetWorkingModule {

    private static final String BASE_URL = "https://api.flickr.com/";

    @Provides
    @Singleton
    public FlickrApi provideApi() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FlickrApi.class);
    }

    @Provides
    @Singleton
    public Picasso providePicasso(Application app) {
        return Picasso.with(app);
    }
}
