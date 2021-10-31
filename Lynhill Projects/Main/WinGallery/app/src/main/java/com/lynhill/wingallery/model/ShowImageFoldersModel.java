package com.lynhill.wingallery.model;

public class ShowImageFoldersModel {
    private String path;
    private String folderName;
    private int numberOfPics = 0;
    private String firstPic;

    public ShowImageFoldersModel() {
    }

    public ShowImageFoldersModel(String path, String folderName) {
        this.path = path;
        this.folderName = folderName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getNumberOfPics() {
        return numberOfPics;
    }

    public void setNumberOfPics(int numberOfPics) {
        this.numberOfPics = numberOfPics;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }
    /*TODO add pics*/
    public void addPics(){this.numberOfPics++;}
}
