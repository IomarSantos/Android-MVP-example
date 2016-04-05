package nl.bhogerheijde.example.rxmvp.view;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public interface View {

    void showProgress();

    void showError(String errorMessage);

    void hideProgress();

}
