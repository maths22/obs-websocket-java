package com.maths22.callbacks;

import com.maths22.OBSWebsocket;
import com.maths22.support.ListSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class __MultiStudioModeChangeCallback   implements StudioModeChangeCallback
{
    public void invoke(OBSWebsocket sender, boolean enabled) {
        List<StudioModeChangeCallback> copy, members = this.getInvocationList();
        synchronized (members)
        {
            copy = new LinkedList<>(members);
        }
        for (Object __dummyForeachVar0 : copy)
        {
            StudioModeChangeCallback d = (StudioModeChangeCallback)__dummyForeachVar0;
            d.invoke(sender, enabled);
        }
    }

    private List<StudioModeChangeCallback> _invocationList = new ArrayList<>();
    public static StudioModeChangeCallback combine(StudioModeChangeCallback a, StudioModeChangeCallback b) {
        if (a == null)
            return b;
         
        if (b == null)
            return a;
         
        __MultiStudioModeChangeCallback ret = new __MultiStudioModeChangeCallback();
        ret._invocationList = a.getInvocationList();
        ret._invocationList.addAll(b.getInvocationList());
        return ret;
    }

    public static StudioModeChangeCallback remove(StudioModeChangeCallback a, StudioModeChangeCallback b) {
        if (a == null || b == null)
            return a;
         
        List<StudioModeChangeCallback> aInvList = a.getInvocationList();
        List<StudioModeChangeCallback> newInvList = ListSupport.removeFinalStretch(aInvList, b.getInvocationList());
        if (aInvList == newInvList)
        {
            return a;
        }
        else
        {
            __MultiStudioModeChangeCallback ret = new __MultiStudioModeChangeCallback();
            ret._invocationList = newInvList;
            return ret;
        } 
    }

    public List<StudioModeChangeCallback> getInvocationList() {
        return _invocationList;
    }

}


