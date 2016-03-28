package nl.bhogerheijde.example.rxmvp;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import nl.bhogerheijde.example.rxmvp.storage.StorageModule;

/**
 * Beacon Scanner, file created on 28/03/16.
 *
 * @author Boyd Hogerheijde
 * @author Mitchell de Vries
 */
@Module(
        injects = App.class,
        includes = StorageModule.class
)
public class AppModule {

    private Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    public Application provideApplicationContext() {
        return app;
    }

}
