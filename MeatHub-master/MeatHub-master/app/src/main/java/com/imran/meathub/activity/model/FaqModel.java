package com.imran.meathub.activity.model;

import android.widget.ImageView;

public class FaqModel {
    public String faqTextView;
    public int rightArrow;

    public FaqModel(String faqTextView,int rightArrow) {
        this.faqTextView=faqTextView;
        this.rightArrow=rightArrow;
    }

    public String getFaqTextView() {
        return faqTextView;
    }

    public int getRightArrow() {
        return rightArrow;
    }

    public void setFaqTextView(String faqTextView) {
        this.faqTextView = faqTextView;
    }

    public void setRightArrow(int rightArrow) {
        this.rightArrow = rightArrow;
    }
}
