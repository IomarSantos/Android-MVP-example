package nl.bhogerheijde.example.rxmvp.ui;

import java.util.List;

import nl.bhogerheijde.example.rxmvp.model.Photo;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface FlickrView {

    void showProgress();

    void setPhotos(List<Photo> photos);

    void showError(Throwable e);

    void hideProgress();

}
