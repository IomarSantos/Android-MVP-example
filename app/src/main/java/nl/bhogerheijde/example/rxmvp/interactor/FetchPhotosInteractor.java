package nl.bhogerheijde.example.rxmvp.interactor;

import nl.bhogerheijde.example.rxmvp.ui.photogallery.OnFetchPhotosListener;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface FetchPhotosInteractor {

    void fetchFlickr(OnFetchPhotosListener listener);

}
