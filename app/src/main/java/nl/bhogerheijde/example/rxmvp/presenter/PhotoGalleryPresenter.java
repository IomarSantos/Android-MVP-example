package nl.bhogerheijde.example.rxmvp.presenter;

import nl.bhogerheijde.example.rxmvp.model.Photo;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface PhotoGalleryPresenter extends BasePresenter {

    void loadImages();

    void onPhotoClicked(Photo photo);

}
