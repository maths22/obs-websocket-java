package com.maths22.data;

import org.json.JSONObject;

/**
* Volume settings of an OBS source
*/
public class VolumeInfo   
{
    /**
    * Source volume in linear scale (0.0 to 1.0)
    */
    private final double volume;
    /**
    * True if source is muted, false otherwise
    */
    private final boolean muted;

    public double getVolume() {
        return volume;
    }

    public boolean isMuted() {
        return muted;
    }

    /**
    * Builds the object from the JSON response body
    * 
    *  @param data JSON response body as a JSONObject
    */
    public VolumeInfo(JSONObject data) {
        volume = data.getDouble("volume");
        muted = data.getBoolean("muted");
    }

}


