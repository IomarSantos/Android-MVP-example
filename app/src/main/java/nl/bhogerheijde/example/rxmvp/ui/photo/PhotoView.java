package nl.bhogerheijde.example.rxmvp.ui.photo;

import android.graphics.Bitmap;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface PhotoView {

    void showProgress();

    void setPhoto(Bitmap photoBitmap);

    void showError(String message);

    void hideProgress();

}
