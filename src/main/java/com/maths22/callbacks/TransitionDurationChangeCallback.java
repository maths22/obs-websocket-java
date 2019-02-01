package com.maths22.callbacks;

import com.maths22.OBSWebsocket;

import java.util.List;

public interface TransitionDurationChangeCallback
{
    void invoke(OBSWebsocket sender, int newDuration) ;

    List<TransitionDurationChangeCallback> getInvocationList() ;

}


