package com.maths22.callbacks;

import com.maths22.OBSWebsocket;
import com.maths22.data.OutputState;

import java.util.List;

public interface OutputStateCallback
{
    void invoke(OBSWebsocket sender, OutputState type) ;

    List<OutputStateCallback> getInvocationList() ;

}


