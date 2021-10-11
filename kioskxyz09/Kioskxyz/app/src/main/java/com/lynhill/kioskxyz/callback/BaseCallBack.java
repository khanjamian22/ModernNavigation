package com.lynhill.kioskxyz.callback;

public interface BaseCallBack<T> {
    void onResponse(T response);
    void onError(String error);
}
