package nl.bhogerheijde.example.rxmvp.ui.photogallery;

import nl.bhogerheijde.example.rxmvp.model.Photo;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface PhotoGalleryPresenter {

    void loadImages();

    void onPhotoClicked(Photo photo);

}
