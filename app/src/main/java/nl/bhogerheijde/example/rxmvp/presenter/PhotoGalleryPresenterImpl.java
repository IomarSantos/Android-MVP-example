package nl.bhogerheijde.example.rxmvp.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import nl.bhogerheijde.example.rxmvp.interactor.DefaultSubscriber;
import nl.bhogerheijde.example.rxmvp.interactor.Interactor;
import nl.bhogerheijde.example.rxmvp.model.Photo;
import nl.bhogerheijde.example.rxmvp.view.PhotoGalleryView;
import nl.bhogerheijde.example.rxmvp.view.View;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class PhotoGalleryPresenterImpl implements PhotoGalleryPresenter {

    private PhotoGalleryView view;
    private Interactor interactor;

    public PhotoGalleryPresenterImpl(Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void loadImages() {
        interactor.execute(new PhotoGallerySubscriber());
    }

    @Override
    public void setView(@NonNull View view) {
        this.view = (PhotoGalleryView) view;
    }

    @Override
    public void finish() {
        interactor.unsubscribe();
    }

    @Override
    public void onPhotoClicked(Photo photo) {
        view.openPhoto(photo);
    }

    private final class PhotoGallerySubscriber extends DefaultSubscriber<List<Photo>> {

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
        public void onNext(List<Photo> photos) {
            view.hideProgress();
            view.setPhotos(photos);
        }

    }

}