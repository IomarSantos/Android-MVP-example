package nl.bhogerheijde.example.rxmvp.interactor;

import java.util.List;

import nl.bhogerheijde.example.rxmvp.model.Flickr;
import nl.bhogerheijde.example.rxmvp.model.Photo;
import nl.bhogerheijde.example.rxmvp.network.FlickrApi;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class FetchFlickrInteractor extends Interactor<List<Photo>> {

    private static final String API_KEY = "e74912fa141cc1590d63e7642ab174ed";

    private FlickrApi api;

    public FetchFlickrInteractor(FlickrApi api) {
        this.api = api;
    }

    @Override
    protected Observable<List<Photo>> getObservable() {
        return api.getRecentPhotos("flickr.photos.getRecent", API_KEY, "json", "1", 500, "url_s, url_k")
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

}
