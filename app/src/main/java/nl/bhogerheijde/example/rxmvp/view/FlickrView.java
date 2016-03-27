package nl.bhogerheijde.example.rxmvp.view;

import java.util.List;

import nl.bhogerheijde.example.rxmvp.model.Photo;

/**
 * Flickr app built with RxJava and MVP pattern, file created on 26/03/16.
 *
 * @author Boyd Hogerheijde
 */
public interface FlickrView {

    void setPhotos(List<Photo> photos);

    void showProgress();

    void hideProgress();

    void showError(Throwable e);

}
