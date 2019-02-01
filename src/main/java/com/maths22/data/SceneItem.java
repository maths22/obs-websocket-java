package com.maths22.data;

import org.json.JSONObject;


/**
* Describes a scene item in an OBS scene
*/
public class SceneItem   
{
    public SceneItem() {
    }

    /**
    * Source name
    */
    private String sourceName = "";
    /**
    * Source internal type
    */
    private String internalType = "";
    /**
    * Source audio volume
    */
    private double audioVolume = 0.0;
    /**
    * Scene item horizontal position/offset
    */
    private double xPos = 0.0;
    /**
    * Scene item vertical position/offset
    */
    private double yPos = 0.0;
    /**
    * Item source width, without scaling and transforms applied
    */
    private int sourceWidth = 0;
    /**
    * Item source height, without scaling and transforms applied
    */
    private int sourceHeight = 0;
    /**
    * Item width
    */
    private double width = 0.0;
    /**
    * Item height
    */
    private double height = 0.0;

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getInternalType() {
        return internalType;
    }

    public void setInternalType(String internalType) {
        this.internalType = internalType;
    }

    public double getAudioVolume() {
        return audioVolume;
    }

    public void setAudioVolume(double audioVolume) {
        this.audioVolume = audioVolume;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public int getSourceWidth() {
        return sourceWidth;
    }

    public void setSourceWidth(int sourceWidth) {
        this.sourceWidth = sourceWidth;
    }

    public int getSourceHeight() {
        return sourceHeight;
    }

    public void setSourceHeight(int sourceHeight) {
        this.sourceHeight = sourceHeight;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    /**
    * Builds the object from the JSON scene description
    * 
    *  @param data JSON item description as a JSONObject
    */
    public SceneItem(JSONObject data) {
        sourceName = data.getString("name");
        internalType = data.getString("type");
        audioVolume = data.getDouble("volume");
        xPos = data.getDouble("x");
        yPos = data.getDouble("y");
        sourceWidth = data.getInt("source_cx");
        sourceHeight = data.getInt("source_cy");
        width = data.getDouble("cx");
        height = data.getDouble("cy");
    }

}


