package nl.bhogerheijde.example.rxmvp.interactor;

import nl.bhogerheijde.example.rxmvp.ui.photogallery.OnFetchFlickrListener;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface FetchFlickrInteractor {

    void fetchFlickr(OnFetchFlickrListener listener);

}
