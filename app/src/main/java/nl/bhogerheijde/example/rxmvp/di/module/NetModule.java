package nl.bhogerheijde.example.rxmvp.di.module;

import android.app.Application;
import android.support.annotation.StringRes;

import com.squareup.picasso.Picasso;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nl.bhogerheijde.example.rxmvp.R;
import nl.bhogerheijde.example.rxmvp.network.FlickrApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Module
public class NetModule {

    private String baseUrl;

    public NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    @Provides
    @Named(value = "api_key")
    String provideApiKey(Application application) {
        return application.getResources().getString(R.string.api_key);
    }

    @Provides
    @Singleton
    FlickrApi provideApi(Retrofit retrofit) {
        return retrofit.create(FlickrApi.class);
    }

    @Provides
    @Singleton
    Picasso providePicasso(Application application) {
        return Picasso.with(application);
    }

}
