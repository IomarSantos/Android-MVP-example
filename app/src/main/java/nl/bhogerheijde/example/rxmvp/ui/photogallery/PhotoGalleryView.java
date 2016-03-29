package nl.bhogerheijde.example.rxmvp.ui.photogallery;

import java.util.List;

import nl.bhogerheijde.example.rxmvp.model.Photo;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface PhotoGalleryView {

    void showProgress();

    void setPhotos(List<Photo> photos);

    void openPhoto(Photo photo);

    void showError(Throwable e);

    void hideProgress();

}
