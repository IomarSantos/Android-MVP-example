package nl.bhogerheijde.example.rxmvp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import nl.bhogerheijde.example.rxmvp.di.component.ApplicationComponent;
import nl.bhogerheijde.example.rxmvp.di.component.DaggerApplicationComponent;
import nl.bhogerheijde.example.rxmvp.di.module.AppModule;
import nl.bhogerheijde.example.rxmvp.di.module.NetModule;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class FlickrApp extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        component = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.flickr.com/"))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return component;
    }

}
