package nl.bhogerheijde.example.rxmvp.presenter;

import nl.bhogerheijde.example.rxmvp.view.IView;

/**
 * Flickr app built with RxJava and MVP pattern, file created on 26/03/16.
 *
 * @author Boyd Hogerheijde
 */
public interface IPresenter<T> {

    void start();

    void finish();

    void setView(IView<T> view);

}
