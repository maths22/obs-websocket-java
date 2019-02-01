package com.maths22.data;

import org.json.JSONObject;

/**
* Data of a stream status update
*/
public class StreamStatus   
{
    /**
    * True if streaming is started and running, false otherwise
    */
    private final boolean streaming;
    /**
    * True if recording is started and running, false otherwise
    */
    private final boolean recording;
    /**
    * Stream bitrate in bytes per second
    */
    private final int bytesPerSec;
    /**
    * Stream bitrate in kilobits per second
    */
    private final int kbitsPerSec;
    /**
    * RTMP output strain
    */
    private final double strain;
    /**
    * Total time since streaming start
    */
    private final int totalStreamTime;
    /**
    * Number of frames sent since streaming start
    */
    private final int totalFrames;
    /**
    * Overall number of frames dropped since streaming start
    */
    private final int droppedFrames;
    /**
    * Current framerate in Frames Per Second
    */
    private final double fps;

    public boolean isStreaming() {
        return streaming;
    }

    public boolean isRecording() {
        return recording;
    }

    public int getBytesPerSec() {
        return bytesPerSec;
    }

    public int getKbitsPerSec() {
        return kbitsPerSec;
    }

    public double getStrain() {
        return strain;
    }

    public int getTotalStreamTime() {
        return totalStreamTime;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public int getDroppedFrames() {
        return droppedFrames;
    }

    public double getFps() {
        return fps;
    }

    /**
    * Builds the object from the JSON event body
    * 
    *  @param data JSON event body as a JSONObject
    */
    public StreamStatus(JSONObject data) {
        streaming = data.getBoolean("streaming");
        recording = data.getBoolean("recording");
        bytesPerSec = data.getInt("bytes-per-sec");
        kbitsPerSec = data.getInt("kbits-per-sec");
        strain = data.getDouble("strain");
        totalStreamTime = data.getInt("total-stream-time");
        totalFrames = data.getInt("num-total-frames");
        droppedFrames = data.getInt("num-dropped-frames");
        fps = data.getDouble("fps");
    }

}


