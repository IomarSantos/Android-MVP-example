package nl.bhogerheijde.example.rxmvp.interactor;

import android.graphics.Bitmap;
import android.net.Uri;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import nl.bhogerheijde.example.rxmvp.ui.photo.OnLoadPhotoListener;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class LoadPhotoInteractorImpl implements LoadPhotoInteractor {

    private Picasso picasso;

    public LoadPhotoInteractorImpl(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void loadPhoto(String url, OnLoadPhotoListener listener) {
        listener.onLoadPhoto(getObservable(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()));
    }

    private Observable<Bitmap> getObservable(String url) {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(picasso.load(Uri.parse(url)).get());
            } catch (IOException e) {
                subscriber.onError(e);
            }
        });
    }

}
