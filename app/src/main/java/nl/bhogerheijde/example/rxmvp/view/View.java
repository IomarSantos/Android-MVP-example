package nl.bhogerheijde.example.rxmvp.view;

/**
 * Game Releases application.
 *
 * @author Mohammed Ali
 * @author Boyd Hogerheijde
 * @author Mitchell de Vries
 */
public interface View {

    void showProgress();

    void showError(String errorMessage);

    void hideProgress();

}
