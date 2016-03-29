package nl.bhogerheijde.example.rxmvp.ui.photo;

import android.graphics.Bitmap;

import rx.Observable;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface OnLoadPhotoListener {

    void onLoadPhoto(Observable<Bitmap> bitmapObservable);

}
