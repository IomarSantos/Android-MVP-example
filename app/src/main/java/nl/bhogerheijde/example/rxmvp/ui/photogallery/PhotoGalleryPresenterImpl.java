package nl.bhogerheijde.example.rxmvp.ui.photogallery;

import java.util.List;

import nl.bhogerheijde.example.rxmvp.api.FlickrApi;
import nl.bhogerheijde.example.rxmvp.model.Flickr;
import nl.bhogerheijde.example.rxmvp.model.Photo;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class PhotoGalleryPresenterImpl implements PhotoGalleryPresenter {

    private static final String API_KEY = "e74912fa141cc1590d63e7642ab174ed";

    private FlickrApi api;
    private PhotoGalleryView view;

    public PhotoGalleryPresenterImpl(FlickrApi api, PhotoGalleryView view) {
        this.api = api;
        this.view = view;
    }

    @Override
    public void loadImages() {
        getObservable().subscribe(getSubscriber());
    }

    @Override
    public void onPhotoClicked(Photo photo) {
        // do something with photo.
        view.openPhoto(photo);
    }

    // Set in an interactor class
    private Observable<List<Photo>> getObservable() {
        return api.getRecentPhotos("flickr.photos.getRecent", API_KEY, "json", "1", "url_s, url_k")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::getPhotos)
                .flatMap(Observable::from)
                .filter(photo -> photo.getUrlLarge() != null)
                .toList();
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
