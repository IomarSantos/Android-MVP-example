package nl.bhogerheijde.example.rxmvp.ui.photogallery;

import java.util.List;

import nl.bhogerheijde.example.rxmvp.interactor.FetchFlickrInteractor;
import nl.bhogerheijde.example.rxmvp.model.Photo;
import rx.Observable;
import rx.Subscriber;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class PhotoGalleryPresenterImpl implements PhotoGalleryPresenter, OnFetchFlickrListener {

    private PhotoGalleryView view;
    private FetchFlickrInteractor interactor;

    public PhotoGalleryPresenterImpl(PhotoGalleryView view, FetchFlickrInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void loadImages() {
        interactor.fetchFlickr(this);
    }

    @Override
    public void onPhotoClicked(Photo photo) {
        // do something with photo.
        view.openPhoto(photo);
    }

    @Override
    public void onFetchFlickr(Observable<List<Photo>> flickrObservable) {
        flickrObservable.subscribe(getSubscriber());
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
