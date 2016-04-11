package nl.bhogerheijde.example.rxmvp.di.component;

import dagger.Subcomponent;
import nl.bhogerheijde.example.rxmvp.activity.PhotoGalleryActivity;
import nl.bhogerheijde.example.rxmvp.di.PerActivity;
import nl.bhogerheijde.example.rxmvp.di.module.PhotoGalleryModule;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@PerActivity
@Subcomponent(modules = PhotoGalleryModule.class)
public interface PhotoGalleryComponent {

    void inject(PhotoGalleryActivity photoGalleryActivity);

}
