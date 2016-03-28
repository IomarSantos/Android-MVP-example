package nl.bhogerheijde.example.rxmvp.domain;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Module(complete = false, library = true)
public class DomainModule {

    @Provides
    @Singleton
    public AnalyticsManager provideAnalyticsManager(Application app) {
        return new AnalyticsManager(app);
    }

}
