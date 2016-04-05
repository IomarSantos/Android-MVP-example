package nl.bhogerheijde.example.rxmvp.interactor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Game Releases application.
 *
 * @author Mohammed Ali
 * @author Boyd Hogerheijde
 * @author Mitchell de Vries
 */
public abstract class Interactor {

    private Subscription subscription = Subscriptions.empty();

    protected abstract Observable getObservable();

    public void execute(Subscriber subscriber) {
        subscription = getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) subscription.unsubscribe();
    }
}
