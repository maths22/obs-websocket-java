package com.maths22.data;

import org.json.JSONObject;

/**
* Version info of the plugin, the API and OBS Studio
*/
public class OBSVersion   
{
    /**
    * obs-websocket plugin version
    */
    private final String pluginVersion;
    /**
    * OBS Studio version
    */
    private final String obsStudioVersion;

    public String getPluginVersion() {
        return pluginVersion;
    }

    public String getObsStudioVersion() {
        return obsStudioVersion;
    }

    /**
    * Builds the object from the JSON response body
    * 
    *  @param data JSON response body as a JSONObject
    */
    public OBSVersion(JSONObject data) {
        pluginVersion = data.getString("obs-websocket-version");
        obsStudioVersion = data.getString("obs-studio-version");
    }

}


