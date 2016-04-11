package nl.bhogerheijde.example.rxmvp.view;

import java.util.List;

import nl.bhogerheijde.example.rxmvp.model.Photo;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface PhotoGalleryView extends BaseView {

    void setPhotos(List<Photo> photos);

    void showPhoto(Photo photo);

}
