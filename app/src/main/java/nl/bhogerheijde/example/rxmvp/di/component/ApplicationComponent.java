package nl.bhogerheijde.example.rxmvp.di.component;

import javax.inject.Singleton;

import dagger.Component;
import nl.bhogerheijde.example.rxmvp.di.module.AppModule;
import nl.bhogerheijde.example.rxmvp.di.module.NetModule;
import nl.bhogerheijde.example.rxmvp.di.module.PhotoGalleryModule;
import nl.bhogerheijde.example.rxmvp.di.module.PhotoModule;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface ApplicationComponent {

    PhotoComponent plus(PhotoModule photoModule);

    PhotoGalleryComponent plus(PhotoGalleryModule photoGalleryModule);

}
