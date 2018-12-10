package com.fit.maid.seminarskiv1.helper;

import java.io.Serializable;

/**
 * Created by mrmai on 11/7/2018.
 */

public interface MyRunnable<T> extends Serializable {
    void Run(T t);
}
