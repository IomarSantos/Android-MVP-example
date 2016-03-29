package nl.bhogerheijde.example.rxmvp.interactor;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
@Module(
        complete = false,
        library = true
)
public class InteractorModule {

    @Provides
    public LoadPhotoInteractor provideLoadPhotoInteractor(Picasso picasso) {
        return new LoadPhotoInteractorImpl(picasso);
    }
}
