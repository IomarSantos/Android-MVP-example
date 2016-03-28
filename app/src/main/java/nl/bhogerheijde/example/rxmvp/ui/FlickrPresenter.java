package nl.bhogerheijde.example.rxmvp.ui;

import nl.bhogerheijde.example.rxmvp.model.Photo;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface FlickrPresenter {

    void start();

    void finish();

    void onPhotoClicked(Photo photo);

}
