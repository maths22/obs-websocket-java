package com.maths22.callbacks;

import com.maths22.OBSWebsocket;
import com.maths22.support.ListSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class __MultiTransitionDurationChangeCallback   implements TransitionDurationChangeCallback
{
    public void invoke(OBSWebsocket sender, int newDuration) {
        List<TransitionDurationChangeCallback> copy, members = this.getInvocationList();
        synchronized (members)
        {
            copy = new LinkedList<>(members);
        }
        for (Object __dummyForeachVar0 : copy)
        {
            TransitionDurationChangeCallback d = (TransitionDurationChangeCallback)__dummyForeachVar0;
            d.invoke(sender, newDuration);
        }
    }

    private List<TransitionDurationChangeCallback> _invocationList = new ArrayList<>();
    public static TransitionDurationChangeCallback combine(TransitionDurationChangeCallback a, TransitionDurationChangeCallback b) {
        if (a == null)
            return b;
         
        if (b == null)
            return a;
         
        __MultiTransitionDurationChangeCallback ret = new __MultiTransitionDurationChangeCallback();
        ret._invocationList = a.getInvocationList();
        ret._invocationList.addAll(b.getInvocationList());
        return ret;
    }

    public static TransitionDurationChangeCallback remove(TransitionDurationChangeCallback a, TransitionDurationChangeCallback b) {
        if (a == null || b == null)
            return a;
         
        List<TransitionDurationChangeCallback> aInvList = a.getInvocationList();
        List<TransitionDurationChangeCallback> newInvList = ListSupport.removeFinalStretch(aInvList, b.getInvocationList());
        if (aInvList == newInvList)
        {
            return a;
        }
        else
        {
            __MultiTransitionDurationChangeCallback ret = new __MultiTransitionDurationChangeCallback();
            ret._invocationList = newInvList;
            return ret;
        } 
    }

    public List<TransitionDurationChangeCallback> getInvocationList() {
        return _invocationList;
    }

}


