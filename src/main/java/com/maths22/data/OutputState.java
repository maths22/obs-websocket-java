package com.maths22.data;

public enum OutputState
{
    /**
    * Describes the state of an output (streaming or recording)
    * 
    * The output is initializing and doesn't produces frames yet
    */
    Starting,
    /**
    * The output is running and produces frames
    */
    Started,
    /**
    * The output is stopping and sends the last remaining frames in its buffer
    */
    Stopping,
    /**
    * The output is completely stopped
    */
    Stopped
}

