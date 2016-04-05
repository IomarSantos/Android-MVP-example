package nl.bhogerheijde.example.rxmvp.interactor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public abstract class Interactor<T> {

    private Subscription subscription = Subscriptions.empty();

    protected abstract Observable<T> getObservable();

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
