package com.maths22.callbacks;

import com.maths22.OBSWebsocket;
import com.maths22.data.StreamStatus;
import com.maths22.support.ListSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class __MultiStreamStatusCallback   implements StreamStatusCallback
{
    public void invoke(OBSWebsocket sender, StreamStatus status) {
        List<StreamStatusCallback> copy, members = this.getInvocationList();
        synchronized (members)
        {
            copy = new LinkedList<>(members);
        }
        for (Object __dummyForeachVar0 : copy)
        {
            StreamStatusCallback d = (StreamStatusCallback)__dummyForeachVar0;
            d.invoke(sender, status);
        }
    }

    private List<StreamStatusCallback> _invocationList = new ArrayList<>();
    public static StreamStatusCallback combine(StreamStatusCallback a, StreamStatusCallback b) {
        if (a == null)
            return b;
         
        if (b == null)
            return a;
         
        __MultiStreamStatusCallback ret = new __MultiStreamStatusCallback();
        ret._invocationList = a.getInvocationList();
        ret._invocationList.addAll(b.getInvocationList());
        return ret;
    }

    public static StreamStatusCallback remove(StreamStatusCallback a, StreamStatusCallback b) {
        if (a == null || b == null)
            return a;
         
        List<StreamStatusCallback> aInvList = a.getInvocationList();
        List<StreamStatusCallback> newInvList = ListSupport.removeFinalStretch(aInvList, b.getInvocationList());
        if (aInvList == newInvList)
        {
            return a;
        }
        else
        {
            __MultiStreamStatusCallback ret = new __MultiStreamStatusCallback();
            ret._invocationList = newInvList;
            return ret;
        } 
    }

    public List<StreamStatusCallback> getInvocationList() {
        return _invocationList;
    }

}


