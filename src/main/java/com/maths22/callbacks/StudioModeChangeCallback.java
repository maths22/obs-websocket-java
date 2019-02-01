package com.maths22.callbacks;

import com.maths22.OBSWebsocket;

import java.util.List;

public interface StudioModeChangeCallback
{
    void invoke(OBSWebsocket sender, boolean enabled) ;

    List<StudioModeChangeCallback> getInvocationList() ;

}


