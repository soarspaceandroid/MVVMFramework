package com.soar.mvvmlib.tools;


import com.soar.mvvmlib.interfaces.AsyncLisenter;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gaofei on 2016/9/13.
 */
public class RxUtils {

    public static <T> void asyncTask(final T tTem ,final AsyncLisenter<T> asyncLisenter){
        Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                T ts = asyncLisenter.runIOThread(tTem);
                subscriber.onNext(ts);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(T t) {
                        asyncLisenter.runMainThread(t);
                    }
                });
    }

}

