package nl.bhogerheijde.example.rxmvp.presenter;

import nl.bhogerheijde.example.rxmvp.view.View;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface Presenter {

    void setView(View view);

    void finish();

}
