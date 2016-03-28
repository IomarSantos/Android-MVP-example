package nl.bhogerheijde.example.rxmvp.domain;

import android.app.Application;
import android.widget.Toast;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class AnalyticsManager {

    private Application app;

    public AnalyticsManager(Application app) {
        this.app = app;
    }

    public void registerAppEnter() {
        Toast.makeText(app, "App enter", Toast.LENGTH_LONG).show();
    }
}
