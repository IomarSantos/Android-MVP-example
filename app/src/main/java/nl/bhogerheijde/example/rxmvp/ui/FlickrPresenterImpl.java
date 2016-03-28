package nl.bhogerheijde.example.rxmvp.ui;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.List;

import nl.bhogerheijde.example.rxmvp.api.FlickrApi;
import nl.bhogerheijde.example.rxmvp.model.Flickr;
import nl.bhogerheijde.example.rxmvp.model.Photo;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class FlickrPresenterImpl implements FlickrPresenter {

    private static final String TAG = "FlickrPresenterImpl";
    private static final String API_KEY = "e74912fa141cc1590d63e7642ab174ed";

    private FlickrApi api;
    private FlickrView view;
    private Subscription subscription;
    private SharedPreferences preferences;

    public FlickrPresenterImpl(FlickrApi api, FlickrView view, SharedPreferences preferences) {
        this.api = api;
        this.view = view;
        this.preferences = preferences;
    }

    @Override
    public void start() {
        subscription = getObservable().map(this::getPhotos).subscribe(getSubscriber());
    }

    @Override
    public void finish() {
        subscription.unsubscribe();
        view = null;
    }

    @Override
    public void onPhotoClicked(Photo photo) {
        // do something with photo.
        // Testing injected prefs
        preferences.edit().putString("TEST", photo.getUrlSmall()).apply();
        Log.d(TAG, "onPhotoClicked: " + preferences.getString("TEST", "PHOTO"));
    }

    private Observable<Flickr> getObservable() {
        return api.getRecentPhotos("flickr.photos.getRecent", API_KEY, "json", "1", "url_s")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private List<Photo> getPhotos(Flickr flickr) {
        return flickr.getPhotosObject().getPhotoList();
    }

    private Subscriber<List<Photo>> getSubscriber() {
        return new Subscriber<List<Photo>>() {
            @Override
            public void onStart() {
                super.onStart();
                view.showProgress();
            }

            @Override
            public void onCompleted() {
                view.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                view.hideProgress();
                view.showError(e);
            }

            @Override
            public void onNext(List<Photo> photos) {
                view.setPhotos(photos);
            }
        };
    }

}
