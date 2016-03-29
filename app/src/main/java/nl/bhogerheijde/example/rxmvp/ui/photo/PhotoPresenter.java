package nl.bhogerheijde.example.rxmvp.ui.photo;

import nl.bhogerheijde.example.rxmvp.model.Photo;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface PhotoPresenter {

    void loadImage(Photo photo);

}
