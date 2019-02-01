package com.maths22.callbacks;

import com.maths22.OBSWebsocket;

import java.util.List;

public interface SceneChangeCallback
{
    void invoke(OBSWebsocket sender, String newSceneName) ;

    List<SceneChangeCallback> getInvocationList() ;

}


