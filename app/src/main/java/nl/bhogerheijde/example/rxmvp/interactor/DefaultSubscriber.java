package nl.bhogerheijde.example.rxmvp.interactor;

import rx.Subscriber;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class DefaultSubscriber<T> extends Subscriber<T> {

    @Override
    public void onStart() {
        // No-op by default
    }

    @Override
    public void onCompleted() {
        // No-op by default
    }

    @Override
    public void onError(Throwable e) {
        // No-op by default
    }

    @Override
    public void onNext(T t) {
        // No-op by default
    }

}
