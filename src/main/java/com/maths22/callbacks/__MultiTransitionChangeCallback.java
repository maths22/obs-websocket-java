package com.maths22.callbacks;

import com.maths22.OBSWebsocket;
import com.maths22.support.ListSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class __MultiTransitionChangeCallback   implements TransitionChangeCallback
{
    public void invoke(OBSWebsocket sender, String newTransitionName) {
        List<TransitionChangeCallback> copy, members = this.getInvocationList();
        synchronized (members)
        {
            copy = new LinkedList<>(members);
        }
        for (Object __dummyForeachVar0 : copy)
        {
            TransitionChangeCallback d = (TransitionChangeCallback)__dummyForeachVar0;
            d.invoke(sender, newTransitionName);
        }
    }

    private List<TransitionChangeCallback> _invocationList = new ArrayList<>();
    public static TransitionChangeCallback combine(TransitionChangeCallback a, TransitionChangeCallback b) {
        if (a == null)
            return b;
         
        if (b == null)
            return a;
         
        __MultiTransitionChangeCallback ret = new __MultiTransitionChangeCallback();
        ret._invocationList = a.getInvocationList();
        ret._invocationList.addAll(b.getInvocationList());
        return ret;
    }

    public static TransitionChangeCallback remove(TransitionChangeCallback a, TransitionChangeCallback b) {
        if (a == null || b == null)
            return a;
         
        List<TransitionChangeCallback> aInvList = a.getInvocationList();
        List<TransitionChangeCallback> newInvList = ListSupport.removeFinalStretch(aInvList, b.getInvocationList());
        if (aInvList == newInvList)
        {
            return a;
        }
        else
        {
            __MultiTransitionChangeCallback ret = new __MultiTransitionChangeCallback();
            ret._invocationList = newInvList;
            return ret;
        } 
    }

    public List<TransitionChangeCallback> getInvocationList() {
        return _invocationList;
    }

}


