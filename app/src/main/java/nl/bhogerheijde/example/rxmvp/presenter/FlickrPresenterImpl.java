package nl.bhogerheijde.example.rxmvp.presenter;

import android.util.Log;

import java.util.List;

import nl.bhogerheijde.example.rxmvp.model.Flickr;
import nl.bhogerheijde.example.rxmvp.model.Photo;
import nl.bhogerheijde.example.rxmvp.service.FlickrService;
import nl.bhogerheijde.example.rxmvp.service.FlickrService.FlickrApi;
import nl.bhogerheijde.example.rxmvp.view.FlickrView;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Flickr app built with RxJava and MVP pattern, file created on 26/03/16.
 *
 * @author Boyd Hogerheijde
 */
public class FlickrPresenterImpl implements FlickrPresenter {

    private static final String TAG = "FlickrPresenterImpl";
    private static final String API_KEY = "e74912fa141cc1590d63e7642ab174ed";

    private FlickrView view;
    private FlickrApi flickrApi;
    private Subscription subscription;

    public FlickrPresenterImpl(FlickrView view) {
        this.view = view;
        flickrApi = FlickrService.getInstance().getFlickrApi();
    }

    @Override
    public void onResume() {
        subscription = getObservable().map(this::getPhotos).subscribe(getSubscriber());
    }

    @Override
    public void onPhotoClicked(Photo photo) {
        // do something with photo.
        Log.d(TAG, "onPhotoClicked: now something is supposed to happen with the photo.");
    }

    @Override
    public void onDestroy() {
        subscription.unsubscribe();
        view = null;
    }

    private Observable<Flickr> getObservable() {
        return flickrApi.getRecentPhotos("flickr.photos.getRecent", API_KEY, "json", "1", "url_s")
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
