package nl.bhogerheijde.example.rxmvp;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import nl.bhogerheijde.example.rxmvp.domain.DomainModule;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Module(
        injects = {
                App.class
        },
        includes = {
                DomainModule.class
        }
)
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public Application provideApplication() {
        return app;
    }
}
