package nl.bhogerheijde.example.rxmvp.view;

/**
 * Flickr app built with RxJava and MVP pattern, file created on 26/03/16.
 *
 * @author Boyd Hogerheijde
 */
public interface IView<T> {
    
    void setPhotos(T model);

    void showProgress();

    void hideProgress();

    void showError(Throwable e);

}
