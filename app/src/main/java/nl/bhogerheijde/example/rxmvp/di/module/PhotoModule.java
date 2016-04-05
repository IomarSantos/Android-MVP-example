package nl.bhogerheijde.example.rxmvp.di.module;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import nl.bhogerheijde.example.rxmvp.di.ActivityScope;
import nl.bhogerheijde.example.rxmvp.interactor.Interactor;
import nl.bhogerheijde.example.rxmvp.interactor.LoadPhotoInteractor;
import nl.bhogerheijde.example.rxmvp.presenter.PhotoPresenter;
import nl.bhogerheijde.example.rxmvp.presenter.PhotoPresenterImpl;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Module
public class PhotoModule {

    private String url;

    public PhotoModule(String url) {
        this.url = url;
    }

    @Provides
    @ActivityScope
    PhotoPresenter providePhotoPresenter(Interactor interactor) {
        return new PhotoPresenterImpl(interactor);
    }

    @Provides
    @ActivityScope
    Interactor provideLoadPhotoInteractor(Picasso picasso) {
        return new LoadPhotoInteractor(url, picasso);
    }

}
