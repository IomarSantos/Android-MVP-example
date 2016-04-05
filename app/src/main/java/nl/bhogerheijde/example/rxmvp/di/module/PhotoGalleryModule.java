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

/**
 * Game Releases application.
 *
 * @author Mohammed Ali
 * @author Boyd Hogerheijde
 * @author Mitchell de Vries
 */
@Module
public class PhotoGalleryModule {

    @Provides
    @PerActivity
    PhotoAdapter providePhotoAdapter(Picasso picasso, Application application) {
        return new PhotoAdapter(picasso, application);
    }

    @Provides
    @PerActivity
    Interactor provideFetchPhotosInteractor(FlickrApi api) {
        return new FetchFlickrInteractor(api);
    }

    @Provides
    @PerActivity
    PhotoGalleryPresenter providePhotoGalleryPresenter(Interactor interactor) {
        return new PhotoGalleryPresenterImpl(interactor);
    }
}
