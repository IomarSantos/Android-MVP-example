package nl.bhogerheijde.example.rxmvp.presenter;

import nl.bhogerheijde.example.rxmvp.view.View;

/**
 * Game Releases application.
 *
 * @author Mohammed Ali
 * @author Boyd Hogerheijde
 * @author Mitchell de Vries
 */
public interface Presenter {

    void setView(View view);

    void finish();

}
