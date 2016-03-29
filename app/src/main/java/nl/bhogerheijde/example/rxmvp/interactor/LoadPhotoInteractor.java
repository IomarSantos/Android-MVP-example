package nl.bhogerheijde.example.rxmvp.interactor;

import nl.bhogerheijde.example.rxmvp.ui.photo.OnDownloadListener;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface LoadPhotoInteractor {

    void loadPhoto(String url, OnDownloadListener listener);

}