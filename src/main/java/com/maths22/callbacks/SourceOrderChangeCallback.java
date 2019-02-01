package com.maths22.callbacks;

import com.maths22.OBSWebsocket;

import java.util.List;

public interface SourceOrderChangeCallback
{
    void invoke(OBSWebsocket sender, String sceneName) ;

    List<SourceOrderChangeCallback> getInvocationList() ;

}


