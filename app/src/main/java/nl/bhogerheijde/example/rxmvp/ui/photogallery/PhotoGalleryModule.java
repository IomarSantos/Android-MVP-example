package nl.bhogerheijde.example.rxmvp.ui.photogallery;

import android.app.Application;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nl.bhogerheijde.example.rxmvp.AppModule;
import nl.bhogerheijde.example.rxmvp.interactor.FetchFlickrInteractor;

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
    public PhotoAdapter providePhotoAdapter(Picasso picasso, PhotoGalleryPresenter presenter, Application application) {
        return new PhotoAdapter(picasso, presenter, application);
    }

    @Provides
    @Singleton
    public PhotoGalleryPresenter providePresenter(PhotoGalleryView view, FetchFlickrInteractor interactor) {
        return new PhotoGalleryPresenterImpl(view, interactor);
    }
}
