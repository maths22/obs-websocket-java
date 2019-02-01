package com.maths22.callbacks;

import com.maths22.OBSWebsocket;
import com.maths22.support.ListSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class __MultiSourceOrderChangeCallback   implements SourceOrderChangeCallback
{
    public void invoke(OBSWebsocket sender, String sceneName) {
        List<SourceOrderChangeCallback> copy, members = this.getInvocationList();
        synchronized (members)
        {
            copy = new LinkedList<>(members);
        }
        for (Object __dummyForeachVar0 : copy)
        {
            SourceOrderChangeCallback d = (SourceOrderChangeCallback)__dummyForeachVar0;
            d.invoke(sender, sceneName);
        }
    }

    private List<SourceOrderChangeCallback> _invocationList = new ArrayList<>();
    public static SourceOrderChangeCallback combine(SourceOrderChangeCallback a, SourceOrderChangeCallback b) {
        if (a == null)
            return b;
         
        if (b == null)
            return a;
         
        __MultiSourceOrderChangeCallback ret = new __MultiSourceOrderChangeCallback();
        ret._invocationList = a.getInvocationList();
        ret._invocationList.addAll(b.getInvocationList());
        return ret;
    }

    public static SourceOrderChangeCallback remove(SourceOrderChangeCallback a, SourceOrderChangeCallback b) {
        if (a == null || b == null)
            return a;
         
        List<SourceOrderChangeCallback> aInvList = a.getInvocationList();
        List<SourceOrderChangeCallback> newInvList = ListSupport.removeFinalStretch(aInvList, b.getInvocationList());
        if (aInvList == newInvList)
        {
            return a;
        }
        else
        {
            __MultiSourceOrderChangeCallback ret = new __MultiSourceOrderChangeCallback();
            ret._invocationList = newInvList;
            return ret;
        } 
    }

    public List<SourceOrderChangeCallback> getInvocationList() {
        return _invocationList;
    }

}


