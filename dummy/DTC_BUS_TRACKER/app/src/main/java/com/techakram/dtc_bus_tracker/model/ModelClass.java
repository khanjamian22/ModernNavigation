package com.techakram.dtc_bus_tracker.model;

public class ModelClass {
    private String name;
    private int imageResource;
    String context;
    public ModelClass(String name,int imageResource,String context){
        this.name=name;
        this.context=context;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }
    public String getContext() {
        return context;
    }
}
