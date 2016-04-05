package nl.bhogerheijde.example.rxmvp.view;

import java.util.List;

import nl.bhogerheijde.example.rxmvp.model.Photo;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface PhotoGalleryView extends View {

    void setPhotos(List<Photo> photos);

    void openPhoto(Photo photo);

}
