package nl.bhogerheijde.example.rxmvp.view;

import android.graphics.Bitmap;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface PhotoView extends BaseView {

    void setPhoto(Bitmap photoBitmap);

}
