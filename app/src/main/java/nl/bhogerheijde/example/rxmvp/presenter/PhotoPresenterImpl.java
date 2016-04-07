package nl.bhogerheijde.example.rxmvp.presenter;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import nl.bhogerheijde.example.rxmvp.interactor.DefaultSubscriber;
import nl.bhogerheijde.example.rxmvp.interactor.Interactor;
import nl.bhogerheijde.example.rxmvp.interactor.LoadPhotoInteractor;
import nl.bhogerheijde.example.rxmvp.model.Photo;
import nl.bhogerheijde.example.rxmvp.view.PhotoView;
import nl.bhogerheijde.example.rxmvp.view.View;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class PhotoPresenterImpl implements PhotoPresenter {

    private PhotoView view;
    private LoadPhotoInteractor interactor;

    public PhotoPresenterImpl(PhotoView view, Interactor interactor) {
        this.view = view;
        this.interactor = (LoadPhotoInteractor) interactor;
    }

    @Override
    public void finish() {
        interactor.unsubscribe();
    }

    @Override
    public void loadImage(Photo photo) {
        view.showProgress();
        interactor.execute(new PhotoSubscriber());
    }

    private final class PhotoSubscriber extends DefaultSubscriber<Bitmap> {

        @Override
        public void onStart() {
            view.showProgress();
        }

        @Override
        public void onCompleted() {
            view.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            view.hideProgress();
            view.showError(e.getMessage());
        }

        @Override
        public void onNext(Bitmap bitmap) {
            view.hideProgress();
            view.setPhoto(bitmap);
        }

    }

}
