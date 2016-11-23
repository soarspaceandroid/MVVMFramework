package com.soar.mvvmlib.interfaces;

/**
 * Created by gaofei on 2016/9/13.
 */
public interface AsyncLisenter<T> {

    public T runIOThread(T t);


    public void runMainThread(T t);

}
