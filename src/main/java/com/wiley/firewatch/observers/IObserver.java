package com.wiley.firewatch.observers;

/**
 * Created by itatsiy on 4/20/2018.
 */
public interface IObserver<T> {
    boolean observe(T har);
}
