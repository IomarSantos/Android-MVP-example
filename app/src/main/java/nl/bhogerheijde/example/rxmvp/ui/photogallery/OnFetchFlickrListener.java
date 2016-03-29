package nl.bhogerheijde.example.rxmvp.ui.photogallery;

import java.util.List;

import nl.bhogerheijde.example.rxmvp.model.Photo;
import rx.Observable;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface OnFetchFlickrListener {

    void onFetchFlickr(Observable<List<Photo>> flickrObservable);

}
