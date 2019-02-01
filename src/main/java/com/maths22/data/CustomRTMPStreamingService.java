package com.maths22.data;

import org.json.JSONObject;

/**
* Custom RTMP settings (fully customizable RTMP credentials)
*/
public class CustomRTMPStreamingService   
{
    public CustomRTMPStreamingService() {
    }

    /**
    * RTMP server URL
    */
    private String serverAddress = "";
    /**
    * RTMP stream key (URL suffix)
    */
    private String streamKey = "";
    /**
    * Tell OBS' RTMP client to authenticate to the server
    */
    private boolean useAuthentication = false;
    /**
    * Username used if authentication is enabled
    */
    private String authUsername = "";
    /**
    * Password used if authentication is enabled
    */
    private String authPassword = "";

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getStreamKey() {
        return streamKey;
    }

    public void setStreamKey(String streamKey) {
        this.streamKey = streamKey;
    }

    public boolean isUseAuthentication() {
        return useAuthentication;
    }

    public void setUseAuthentication(boolean useAuthentication) {
        this.useAuthentication = useAuthentication;
    }

    public String getAuthUsername() {
        return authUsername;
    }

    public void setAuthUsername(String authUsername) {
        this.authUsername = authUsername;
    }

    public String getAuthPassword() {
        return authPassword;
    }

    public void setAuthPassword(String authPassword) {
        this.authPassword = authPassword;
    }

    /**
    * Construct object from data provided by StreamingService.Settings
    * 
    *  @param settings
    */
    public CustomRTMPStreamingService(JSONObject settings) {
        serverAddress = settings.getString("server");
        streamKey = settings.getString("key");
        useAuthentication = settings.getBoolean("use_auth");
        authUsername = settings.getString("username");
        authPassword = settings.getString("password");
    }

    /**
    * Convert to JSON object
    * 
    *  @return
    */
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("server", serverAddress);
        obj.put("key", streamKey);
        obj.put("use_auth", useAuthentication);
        obj.put("username", authUsername);
        obj.put("password", authPassword);
        return obj;
    }

}


