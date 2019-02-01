package com.maths22.callbacks;

import com.maths22.OBSWebsocket;
import com.maths22.support.ListSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class __MultiSceneChangeCallback   implements SceneChangeCallback
{
    public void invoke(OBSWebsocket sender, String newSceneName) {
        List<SceneChangeCallback> copy, members = this.getInvocationList();
        synchronized (members)
        {
            copy = new LinkedList<>(members);
        }
        for (Object __dummyForeachVar0 : copy)
        {
            SceneChangeCallback d = (SceneChangeCallback)__dummyForeachVar0;
            d.invoke(sender, newSceneName);
        }
    }

    private List<SceneChangeCallback> _invocationList = new ArrayList<>();
    public static SceneChangeCallback combine(SceneChangeCallback a, SceneChangeCallback b) {
        if (a == null)
            return b;
         
        if (b == null)
            return a;
         
        __MultiSceneChangeCallback ret = new __MultiSceneChangeCallback();
        ret._invocationList = a.getInvocationList();
        ret._invocationList.addAll(b.getInvocationList());
        return ret;
    }

    public static SceneChangeCallback remove(SceneChangeCallback a, SceneChangeCallback b) {
        if (a == null || b == null)
            return a;

        List<SceneChangeCallback> aInvList = a.getInvocationList();
        List<SceneChangeCallback> newInvList = ListSupport.removeFinalStretch(aInvList, b.getInvocationList());

        if (aInvList == newInvList)
        {
            return a;
        }
        else
        {
            __MultiSceneChangeCallback ret = new __MultiSceneChangeCallback();
            ret._invocationList = newInvList;
            return ret;
        } 
    }

    public List<SceneChangeCallback> getInvocationList() {
        return _invocationList;
    }

}