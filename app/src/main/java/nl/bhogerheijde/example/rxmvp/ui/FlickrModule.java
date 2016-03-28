package nl.bhogerheijde.example.rxmvp.ui;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nl.bhogerheijde.example.rxmvp.AppModule;
import nl.bhogerheijde.example.rxmvp.api.FlickrApi;
import nl.bhogerheijde.example.rxmvp.api.NetModule;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Module(
        injects = {
                FlickrActivity.class
        },
        includes = {
                NetModule.class
        },
        complete = false,
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
    public FlickrPresenter providePresenter(FlickrApi api, FlickrView view) {
        return new FlickrPresenterImpl(api, view);
    }
}
