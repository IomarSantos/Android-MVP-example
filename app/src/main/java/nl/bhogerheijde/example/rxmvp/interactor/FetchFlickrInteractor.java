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

    private FlickrApi api;
    private String key;

    public FetchFlickrInteractor(FlickrApi api, String key) {
        this.api = api;
        this.key = key;
    }

    @Override
    protected Observable<List<Photo>> getObservable() {
        return api.getRecentPhotos("flickr.photos.getRecent", key, "json", "1", 500, "url_s, url_k")
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
