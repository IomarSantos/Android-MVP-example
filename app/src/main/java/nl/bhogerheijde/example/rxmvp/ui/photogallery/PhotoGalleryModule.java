package nl.bhogerheijde.example.rxmvp.ui.photogallery;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nl.bhogerheijde.example.rxmvp.AppModule;
import nl.bhogerheijde.example.rxmvp.interactor.FetchPhotosInteractor;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Module(
        injects = PhotoGalleryActivity.class,
        addsTo = AppModule.class
)
public class PhotoGalleryModule {

    private PhotoGalleryView view;

    public PhotoGalleryModule(PhotoGalleryView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public PhotoGalleryView provideView() {
        return view;
    }

    @Provides
    @Singleton
    public PhotoGalleryPresenter providePresenter(PhotoGalleryView view, FetchPhotosInteractor interactor) {
        return new PhotoGalleryPresenterImpl(view, interactor);
    }
}
