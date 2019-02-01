package com.maths22.callbacks;

import com.maths22.OBSWebsocket;

import java.util.List;

public interface SceneItemUpdateCallback   
{
    void invoke(OBSWebsocket sender, String sceneName, String itemName) ;

    List<SceneItemUpdateCallback> getInvocationList() ;

}


