package nl.bhogerheijde.example.rxmvp.di.module;

import android.app.Application;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import nl.bhogerheijde.example.rxmvp.adapter.PhotoAdapter;
import nl.bhogerheijde.example.rxmvp.di.ActivityScope;
import nl.bhogerheijde.example.rxmvp.interactor.FetchFlickrInteractor;
import nl.bhogerheijde.example.rxmvp.interactor.Interactor;
import nl.bhogerheijde.example.rxmvp.network.FlickrApi;
import nl.bhogerheijde.example.rxmvp.presenter.PhotoGalleryPresenter;
import nl.bhogerheijde.example.rxmvp.presenter.PhotoGalleryPresenterImpl;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Module
public class PhotoGalleryModule {

    @Provides
    @ActivityScope
    PhotoAdapter providePhotoAdapter(Picasso picasso, Application application) {
        return new PhotoAdapter(picasso, application);
    }

    @Provides
    @ActivityScope
    Interactor provideFetchPhotosInteractor(FlickrApi api) {
        return new FetchFlickrInteractor(api);
    }

    @Provides
    @ActivityScope
    PhotoGalleryPresenter providePhotoGalleryPresenter(Interactor interactor) {
        return new PhotoGalleryPresenterImpl(interactor);
    }

}
