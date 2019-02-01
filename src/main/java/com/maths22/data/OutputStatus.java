package com.maths22.data;


import org.json.JSONObject;

/**
* Status of streaming output and recording output
*/
public class OutputStatus   
{
    /**
    * True if streaming is started and running, false otherwise
    */
    private final boolean streaming;
    /**
    * True if recording is started and running, false otherwise
    */
    private final boolean recording;

    public boolean isStreaming() {
        return streaming;
    }

    public boolean isRecording() {
        return recording;
    }

    /**
    * Builds the object from the JSON response body
    * 
    *  @param data JSON response body as a JSONObject
    */
    public OutputStatus(JSONObject data) {
        streaming = data.getBoolean("streaming");
        recording = data.getBoolean("recording");
    }

}


