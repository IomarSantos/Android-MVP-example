package nl.bhogerheijde.example.rxmvp.di.module;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import nl.bhogerheijde.example.rxmvp.di.PerActivity;
import nl.bhogerheijde.example.rxmvp.interactor.Interactor;
import nl.bhogerheijde.example.rxmvp.interactor.LoadPhotoInteractor;
import nl.bhogerheijde.example.rxmvp.presenter.PhotoPresenter;
import nl.bhogerheijde.example.rxmvp.presenter.PhotoPresenterImpl;
import nl.bhogerheijde.example.rxmvp.view.PhotoView;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Module
public class PhotoModule {

    private String url;
    private PhotoView view;

    public PhotoModule(PhotoView view, String url) {
        this.view = view;
        this.url = url;
    }

    @Provides
    @PerActivity
    PhotoPresenter providePhotoPresenter(Interactor interactor) {
        return new PhotoPresenterImpl(view, interactor);
    }

    @Provides
    @PerActivity
    Interactor provideLoadPhotoInteractor(Picasso picasso) {
        return new LoadPhotoInteractor(url, picasso);
    }

}
