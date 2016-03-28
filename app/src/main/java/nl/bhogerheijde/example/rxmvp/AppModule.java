package nl.bhogerheijde.example.rxmvp;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import nl.bhogerheijde.example.rxmvp.storage.StorageModule;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Module(
        injects = App.class,
        includes = StorageModule.class
)
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public Application provideApplicationContext() {
        return app;
    }

}
