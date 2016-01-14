package com.example.nbaumgartner.yodatalk;

/**
 * Created by nbaumgartner on 1/14/2016.
 */
public interface Listener<T> {
    public void onTaskComplete(T result, int number);
}
