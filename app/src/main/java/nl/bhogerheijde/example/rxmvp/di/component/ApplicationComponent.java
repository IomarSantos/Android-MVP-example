package nl.bhogerheijde.example.rxmvp.di.component;

import javax.inject.Singleton;

import dagger.Component;
import nl.bhogerheijde.example.rxmvp.di.module.AppModule;
import nl.bhogerheijde.example.rxmvp.di.module.NetModule;
import nl.bhogerheijde.example.rxmvp.di.module.PhotoGalleryModule;
import nl.bhogerheijde.example.rxmvp.di.module.PhotoModule;

/**
 * Game Releases application.
 *
 * @author Mohammed Ali
 * @author Boyd Hogerheijde
 * @author Mitchell de Vries
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface ApplicationComponent {

    PhotoSubComponent plus(PhotoModule photoModule);

    PhotoGallerySubComponent plus(PhotoGalleryModule photoGalleryModule);

}
