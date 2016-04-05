package nl.bhogerheijde.example.rxmvp.interactor;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import rx.Observable;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class LoadPhotoInteractor extends Interactor {

    private String url;
    private Picasso picasso;

    public LoadPhotoInteractor(String url, Picasso picasso) {
        this.url = url;
        this.picasso = picasso;
    }

    @Override
    protected Observable getObservable() {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(picasso.load(url).get());
            } catch (IOException e) {
                subscriber.onError(e);
            }
        });
    }
}
