package com.maths22.callbacks;

import com.maths22.OBSWebsocket;
import com.maths22.data.OutputState;
import com.maths22.support.ListSupport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class __MultiOutputStateCallback   implements OutputStateCallback
{
    public void invoke(OBSWebsocket sender, OutputState type) {
        List<OutputStateCallback> copy, members = this.getInvocationList();
        synchronized (members)
        {
            copy = new LinkedList<>(members);
        }
        for (Object __dummyForeachVar0 : copy)
        {
            OutputStateCallback d = (OutputStateCallback)__dummyForeachVar0;
            d.invoke(sender, type);
        }
    }

    private List<OutputStateCallback> _invocationList = new ArrayList<>();
    public static OutputStateCallback combine(OutputStateCallback a, OutputStateCallback b) {
        if (a == null)
            return b;
         
        if (b == null)
            return a;
         
        __MultiOutputStateCallback ret = new __MultiOutputStateCallback();
        ret._invocationList = a.getInvocationList();
        ret._invocationList.addAll(b.getInvocationList());
        return ret;
    }

    public static OutputStateCallback remove(OutputStateCallback a, OutputStateCallback b) {
        if (a == null || b == null)
            return a;

        List<OutputStateCallback> aInvList = a.getInvocationList();
        List<OutputStateCallback> newInvList = ListSupport.removeFinalStretch(aInvList, b.getInvocationList());
        if (aInvList == newInvList)
        {
            return a;
        }
        else
        {
            __MultiOutputStateCallback ret = new __MultiOutputStateCallback();
            ret._invocationList = newInvList;
            return ret;
        } 
    }

    public List<OutputStateCallback> getInvocationList() {
        return _invocationList;
    }

}


