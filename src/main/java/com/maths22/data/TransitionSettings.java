package com.maths22.data;

import org.json.JSONObject;

/**
* Current transition settings
*/
public class TransitionSettings   
{
    /**
    * Transition name
    */
    private final String name;
    /**
    * Transition duration in milliseconds
    */
    private final int duration;

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    /**
    * Builds the object from the JSON response body
    * 
    *  @param data JSON response body as a JSONObject
    */
    public TransitionSettings(JSONObject data) {
        name = data.getString("name");
        duration = data.getInt("duration");
    }

}


