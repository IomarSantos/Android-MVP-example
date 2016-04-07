package nl.bhogerheijde.example.rxmvp;

import android.app.Application;

import nl.bhogerheijde.example.rxmvp.di.component.ApplicationComponent;
import nl.bhogerheijde.example.rxmvp.di.component.DaggerApplicationComponent;
import nl.bhogerheijde.example.rxmvp.di.component.PhotoComponent;
import nl.bhogerheijde.example.rxmvp.di.component.PhotoGalleryComponent;
import nl.bhogerheijde.example.rxmvp.di.module.AppModule;
import nl.bhogerheijde.example.rxmvp.di.module.NetModule;
import nl.bhogerheijde.example.rxmvp.di.module.PhotoGalleryModule;
import nl.bhogerheijde.example.rxmvp.di.module.PhotoModule;
import nl.bhogerheijde.example.rxmvp.view.PhotoGalleryView;
import nl.bhogerheijde.example.rxmvp.view.PhotoView;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class FlickrApp extends Application {

    private ApplicationComponent applicationComponent;
    private PhotoGalleryComponent photoGalleryComponent;
    private PhotoComponent photoComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.flickr.com/"))
                .build();
    }

    public PhotoGalleryComponent createPhotoGalleryComponent(PhotoGalleryView view) {
        photoGalleryComponent = applicationComponent.plus(new PhotoGalleryModule(view));
        return photoGalleryComponent;
    }

    public PhotoComponent createPhotoComponent(PhotoView view, String url) {
        photoComponent = applicationComponent.plus(new PhotoModule(view, url));
        return photoComponent;
    }

    public void releasePhotoGalleryComponent() {
        photoGalleryComponent = null;
    }

    public void releasePhotoComponent() {
        photoComponent = null;
    }

}
