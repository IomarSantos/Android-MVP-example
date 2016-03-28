package nl.bhogerheijde.example.rxmvp.ui;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nl.bhogerheijde.example.rxmvp.AppModule;
import nl.bhogerheijde.example.rxmvp.api.FlickrApi;
import nl.bhogerheijde.example.rxmvp.api.NetWorkingModule;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Module(
        injects = FlickrActivity.class,
        includes = NetWorkingModule.class,
        addsTo = AppModule.class
)
public class FlickrModule {

    private FlickrView view;

    public FlickrModule(FlickrView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public FlickrView provideView() {
        return view;
    }

    @Provides
    @Singleton
    public FlickrPresenter providePresenter(FlickrApi api, FlickrView view, SharedPreferences preferences) {
        return new FlickrPresenterImpl(api, view, preferences);
    }
}
