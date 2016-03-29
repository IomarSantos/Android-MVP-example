package nl.bhogerheijde.example.rxmvp.interactor;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import nl.bhogerheijde.example.rxmvp.api.FlickrApi;

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
    public FetchPhotosInteractor provideFetchPhotosInteractor(FlickrApi api) {
        return new FetchPhotosInteractorImpl(api);
    }

    @Provides
    public LoadPhotoInteractor provideLoadPhotoInteractor(Picasso picasso) {
        return new LoadPhotoInteractorImpl(picasso);
    }

}
