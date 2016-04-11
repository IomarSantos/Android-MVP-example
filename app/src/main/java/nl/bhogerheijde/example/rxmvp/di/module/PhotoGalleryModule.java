package nl.bhogerheijde.example.rxmvp.di.module;

import android.app.Application;

import com.squareup.picasso.Picasso;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import nl.bhogerheijde.example.rxmvp.adapter.PhotoAdapter;
import nl.bhogerheijde.example.rxmvp.di.PerActivity;
import nl.bhogerheijde.example.rxmvp.interactor.FetchFlickrInteractor;
import nl.bhogerheijde.example.rxmvp.interactor.Interactor;
import nl.bhogerheijde.example.rxmvp.network.FlickrApi;
import nl.bhogerheijde.example.rxmvp.presenter.PhotoGalleryPresenter;
import nl.bhogerheijde.example.rxmvp.presenter.PhotoGalleryPresenterImpl;
import nl.bhogerheijde.example.rxmvp.view.PhotoGalleryView;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Module
public class PhotoGalleryModule {

    private PhotoGalleryView view;

    public PhotoGalleryModule(PhotoGalleryView view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    PhotoAdapter providePhotoAdapter(Picasso picasso, Application application) {
        return new PhotoAdapter(picasso, application);
    }

    @Provides
    @PerActivity
    Interactor provideFetchPhotosInteractor(FlickrApi api, @Named("api_key") String key) {
        return new FetchFlickrInteractor(api, key);
    }

    @Provides
    @PerActivity
    PhotoGalleryPresenter providePhotoGalleryPresenter(Interactor interactor) {
        return new PhotoGalleryPresenterImpl(view, interactor);
    }

}
