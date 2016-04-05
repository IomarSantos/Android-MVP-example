package nl.bhogerheijde.example.rxmvp.presenter;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import nl.bhogerheijde.example.rxmvp.interactor.Interactor;
import nl.bhogerheijde.example.rxmvp.interactor.LoadPhotoInteractor;
import nl.bhogerheijde.example.rxmvp.model.Photo;
import nl.bhogerheijde.example.rxmvp.view.PhotoView;
import nl.bhogerheijde.example.rxmvp.view.View;
import rx.Subscriber;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class PhotoPresenterImpl implements PhotoPresenter {

    private PhotoView view;
    private LoadPhotoInteractor interactor;

    public PhotoPresenterImpl(Interactor interactor) {
        this.interactor = (LoadPhotoInteractor) interactor;
    }

    @Override
    public void setView(@NonNull View view) {
        this.view = (PhotoView) view;
    }

    @Override
    public void finish() {
        interactor.unsubscribe();
    }

    @Override
    public void loadImage(Photo photo) {
        view.showProgress();
        interactor.execute(getSubscriber());
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
                if (view != null) view.hideProgress();
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
