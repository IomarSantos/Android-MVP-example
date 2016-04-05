package nl.bhogerheijde.example.rxmvp.di.component;

import dagger.Subcomponent;
import nl.bhogerheijde.example.rxmvp.activity.PhotoGalleryActivity;
import nl.bhogerheijde.example.rxmvp.di.PerActivity;
import nl.bhogerheijde.example.rxmvp.di.module.PhotoGalleryModule;

/**
 * Game Releases application.
 *
 * @author Mohammed Ali
 * @author Boyd Hogerheijde
 * @author Mitchell de Vries
 */
@PerActivity
@Subcomponent(modules = PhotoGalleryModule.class)
public interface PhotoGallerySubComponent {

    void inject(PhotoGalleryActivity photoGalleryActivity);

}
