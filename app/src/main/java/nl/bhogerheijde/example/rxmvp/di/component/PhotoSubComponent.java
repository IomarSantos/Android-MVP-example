package nl.bhogerheijde.example.rxmvp.di.component;

import dagger.Subcomponent;
import nl.bhogerheijde.example.rxmvp.activity.PhotoActivity;
import nl.bhogerheijde.example.rxmvp.di.PerActivity;
import nl.bhogerheijde.example.rxmvp.di.module.PhotoModule;

/**
 * Game Releases application.
 *
 * @author Mohammed Ali
 * @author Boyd Hogerheijde
 * @author Mitchell de Vries
 */
@PerActivity
@Subcomponent(modules = PhotoModule.class)
public interface PhotoSubComponent {

    void inject(PhotoActivity photoActivity);

}
