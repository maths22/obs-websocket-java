package com.maths22.callbacks;

import com.maths22.OBSWebsocket;
import com.maths22.support.ListSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class __MultiSceneItemUpdateCallback   implements SceneItemUpdateCallback
{
    public void invoke(OBSWebsocket sender, String sceneName, String itemName) {
        List<SceneItemUpdateCallback> copy, members = this.getInvocationList();
        synchronized (members)
        {
            copy = new LinkedList<>(members);
        }
        for (Object __dummyForeachVar0 : copy)
        {
            SceneItemUpdateCallback d = (SceneItemUpdateCallback)__dummyForeachVar0;
            d.invoke(sender, sceneName, itemName);
        }
    }

    private List<SceneItemUpdateCallback> _invocationList = new ArrayList<>();
    public static SceneItemUpdateCallback combine(SceneItemUpdateCallback a, SceneItemUpdateCallback b) {
        if (a == null)
            return b;
         
        if (b == null)
            return a;
         
        __MultiSceneItemUpdateCallback ret = new __MultiSceneItemUpdateCallback();
        ret._invocationList = a.getInvocationList();
        ret._invocationList.addAll(b.getInvocationList());
        return ret;
    }

    public static SceneItemUpdateCallback remove(SceneItemUpdateCallback a, SceneItemUpdateCallback b) {
        if (a == null || b == null)
            return a;
         
        List<SceneItemUpdateCallback> aInvList = a.getInvocationList();
        List<SceneItemUpdateCallback> newInvList = ListSupport.removeFinalStretch(aInvList, b.getInvocationList());
        if (aInvList == newInvList)
        {
            return a;
        }
        else
        {
            __MultiSceneItemUpdateCallback ret = new __MultiSceneItemUpdateCallback();
            ret._invocationList = newInvList;
            return ret;
        } 
    }

    public List<SceneItemUpdateCallback> getInvocationList() {
        return _invocationList;
    }

}


