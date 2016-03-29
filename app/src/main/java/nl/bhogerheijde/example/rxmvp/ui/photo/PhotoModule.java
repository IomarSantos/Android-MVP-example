package nl.bhogerheijde.example.rxmvp.ui.photo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nl.bhogerheijde.example.rxmvp.AppModule;
import nl.bhogerheijde.example.rxmvp.interactor.LoadPhotoInteractor;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Module(
        injects = PhotoActivity.class,
        addsTo = AppModule.class
)
public class PhotoModule {

    private PhotoView view;

    public PhotoModule(PhotoView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public PhotoView providePhotoView() {
        return view;
    }

    @Provides
    @Singleton
    public PhotoPresenter providePhotoPresenter(PhotoView view, LoadPhotoInteractor interactor) {
        return new PhotoPresenterImpl(view, interactor);
    }
}
