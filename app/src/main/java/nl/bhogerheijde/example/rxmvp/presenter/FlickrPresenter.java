package nl.bhogerheijde.example.rxmvp.presenter;

import nl.bhogerheijde.example.rxmvp.model.Flickr;
import nl.bhogerheijde.example.rxmvp.service.FlickrService;
import nl.bhogerheijde.example.rxmvp.view.IView;
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
public class FlickrPresenter implements IPresenter<Flickr> {

    private static final String API_KEY = "e74912fa141cc1590d63e7642ab174ed";

    private IView<Flickr> view;
    private Subscription subscription;
    private FlickrService.FlickrApi flickrApi;

    public FlickrPresenter(FlickrService.FlickrApi flickrApi) {
        this.flickrApi = flickrApi;
    }

    @Override
    public void start() {
        subscription = getObservable().subscribe(getSubscriber());
    }

    @Override
    public void finish() {
        subscription.unsubscribe();
        view = null;
    }

    @Override
    public void setView(IView<Flickr> view) {
        this.view = view;
    }

    private Observable<Flickr> getObservable() {
        return flickrApi.getRecentPhotos("flickr.photos.getRecent", API_KEY, "json", "1", "url_s")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Subscriber<Flickr> getSubscriber() {
        return new Subscriber<Flickr>() {
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
            public void onNext(Flickr flickr) {
                view.setPhotos(flickr);
            }
        };
    }
}
