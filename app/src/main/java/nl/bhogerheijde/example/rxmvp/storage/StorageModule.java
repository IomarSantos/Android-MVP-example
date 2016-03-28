package nl.bhogerheijde.example.rxmvp.storage;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Module(
        complete = false,
        library = true
)
public class StorageModule {

    @Provides
    @Singleton
    public SharedPreferences providePreferences(Application app) {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }
}
