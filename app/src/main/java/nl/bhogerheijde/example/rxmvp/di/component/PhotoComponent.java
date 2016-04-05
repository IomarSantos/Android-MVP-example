package nl.bhogerheijde.example.rxmvp.di.component;

import dagger.Subcomponent;
import nl.bhogerheijde.example.rxmvp.activity.PhotoActivity;
import nl.bhogerheijde.example.rxmvp.di.ActivityScope;
import nl.bhogerheijde.example.rxmvp.di.module.PhotoModule;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@ActivityScope
@Subcomponent(modules = PhotoModule.class)
public interface PhotoComponent {

    void inject(PhotoActivity photoActivity);

}
