package com.techakram.Online_Shop.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>( );
        mText.setValue("This is akram fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}