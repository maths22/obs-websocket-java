package com.maths22.data;

/**
* Crop coordinates for a scene item
*/
public class SceneItemCropInfo   
{
    public SceneItemCropInfo() {
    }

    /**
    * Top crop (in pixels)
    */
    private int top = 0;
    /**
    * Bottom crop (in pixels)
    */
    private int bottom = 0;
    /**
    * Left crop (in pixels)
    */
    private int left = 0;
    /**
    * Right crop (in pixels)
    */
    private int right = 0;

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}


