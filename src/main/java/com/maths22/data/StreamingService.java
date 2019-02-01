package com.maths22.data;

import org.json.JSONObject;

/**
* Streaming settings
*/
public class StreamingService   
{
    public StreamingService() {
    }

    /**
    * Type of streaming service
    */
    private String type = "";
    /**
    * Streaming service settings (JSON data)
    */
    private JSONObject settings = new JSONObject();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONObject getSettings() {
        return settings;
    }

    public void setSettings(JSONObject settings) {
        this.settings = settings;
    }
}


