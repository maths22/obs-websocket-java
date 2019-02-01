package com.maths22.callbacks;

import com.maths22.OBSWebsocket;
import com.maths22.data.StreamStatus;

import java.util.List;

public interface StreamStatusCallback
{
    void invoke(OBSWebsocket sender, StreamStatus status) ;

    List<StreamStatusCallback> getInvocationList() ;

}


