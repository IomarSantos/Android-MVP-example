package nl.bhogerheijde.example.rxmvp.ui.photo;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import nl.bhogerheijde.example.rxmvp.interactor.LoadPhotoInteractor;
import nl.bhogerheijde.example.rxmvp.model.Photo;
import rx.Observable;
import rx.Subscriber;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class PhotoPresenterImpl implements PhotoPresenter, OnDownloadListener {

    private PhotoView view;
    private LoadPhotoInteractor interactor;

    public PhotoPresenterImpl(PhotoView view, LoadPhotoInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void loadImage(Photo photo) {
        view.showProgress();
        interactor.loadPhoto(photo.getUrlLarge(), this);
    }

    @Override
    public void onDownloadImage(Observable<Bitmap> bitmapObservable) {
        bitmapObservable.subscribe(getSubscriber());
    }

    @NonNull
    private Subscriber<Bitmap> getSubscriber() {
        return new Subscriber<Bitmap>() {
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
                view.showError(e.getMessage());
                view.hideProgress();
            }

            @Override
            public void onNext(Bitmap bitmap) {
                view.setPhoto(bitmap);
                view.hideProgress();
            }
        };
    }
}
