package com.example.mypractice.callback;

public interface BaseCallBack<T> {
    void onResponse(T response);
    void onError(String error);
}
