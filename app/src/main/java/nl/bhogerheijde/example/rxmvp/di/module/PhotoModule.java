package nl.bhogerheijde.example.rxmvp.di.module;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import nl.bhogerheijde.example.rxmvp.di.PerActivity;
import nl.bhogerheijde.example.rxmvp.interactor.Interactor;
import nl.bhogerheijde.example.rxmvp.interactor.LoadPhotoInteractor;
import nl.bhogerheijde.example.rxmvp.presenter.PhotoPresenter;
import nl.bhogerheijde.example.rxmvp.presenter.PhotoPresenterImpl;

/**
 * Game Releases application.
 *
 * @author Mohammed Ali
 * @author Boyd Hogerheijde
 * @author Mitchell de Vries
 */
@Module
public class PhotoModule {

    private String url;

    public PhotoModule(String url) {
        this.url = url;
    }

    @Provides
    @PerActivity
    PhotoPresenter providePhotoPresenter(Interactor interactor) {
        return new PhotoPresenterImpl(interactor);
    }

    @Provides
    @PerActivity
    Interactor provideLoadPhotoInteractor(Picasso picasso) {
        return new LoadPhotoInteractor(url, picasso);
    }
}
