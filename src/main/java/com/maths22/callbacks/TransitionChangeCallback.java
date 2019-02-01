package com.maths22.callbacks;

import com.maths22.OBSWebsocket;

import java.util.List;

public interface TransitionChangeCallback
{
    void invoke(OBSWebsocket sender, String newTransitionName) ;

    List<TransitionChangeCallback> getInvocationList() ;

}


