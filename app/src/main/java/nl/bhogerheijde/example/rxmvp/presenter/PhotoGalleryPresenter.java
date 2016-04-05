package nl.bhogerheijde.example.rxmvp.presenter;

import nl.bhogerheijde.example.rxmvp.model.Photo;
import nl.bhogerheijde.example.rxmvp.view.PhotoGalleryView;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface PhotoGalleryPresenter extends Presenter {

    void loadImages();

    void onPhotoClicked(Photo photo);

}
