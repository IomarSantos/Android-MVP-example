package nl.bhogerheijde.example.rxmvp.ui.photogallery;

import java.util.List;

import nl.bhogerheijde.example.rxmvp.model.Photo;
import rx.Observable;

/**
 * Beacon Scanner, file created on 29/03/16.
 *
 * @author Boyd Hogerheijde
 * @author Mitchell de Vries
 */
public interface OnFetchPhotosListener {

    void onFetchPhotos(Observable<List<Photo>> flickrObservable);

}
