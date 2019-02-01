package com.maths22.data;

import org.json.JSONObject;

/**
* Common RTMP settings (predefined streaming services list)
*/
public class CommonRTMPStreamingService   
{
    public CommonRTMPStreamingService() {
    }

    /**
    * Streaming provider name
    */
    private String serviceName = "";
    /**
    * Streaming server URL;
    */
    private String serverUrl = "";
    /**
    * Stream key
    */
    private String streamKey = "";

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getStreamKey() {
        return streamKey;
    }

    public void setStreamKey(String streamKey) {
        this.streamKey = streamKey;
    }

    /**
    * Construct object from data provided by StreamingService.Settings
    * 
    *  @param settings
    */
    public CommonRTMPStreamingService(JSONObject settings) {
        serviceName = settings.getString("service");
        serverUrl = settings.getString("server");
        streamKey = settings.getString("key");
    }

    /**
    * Convert to JSON object
    * 
    *  @return
    */
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("service", serviceName);
        obj.put("server", serverUrl);
        obj.put("key", streamKey);
        return obj;
    }

}


