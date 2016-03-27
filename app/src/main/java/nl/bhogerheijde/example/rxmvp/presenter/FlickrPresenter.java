package nl.bhogerheijde.example.rxmvp.presenter;

import nl.bhogerheijde.example.rxmvp.model.Photo;

/**
 * Flickr app built with RxJava and MVP pattern, file created on 26/03/16.
 *
 * @author Boyd Hogerheijde
 */
public interface FlickrPresenter {

    void onResume();

    void onPhotoClicked(Photo photo);

    void onDestroy();

}
