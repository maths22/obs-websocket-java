package com.maths22.data;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OBSScene   
{
    private final String name;
    private final List<SceneItem> items;

    public String getName() {
        return name;
    }

    public List<SceneItem> getItems() {
        return items;
    }

    /**
    * Builds the object from the JSON description
    * 
    *  @param data JSON scene description as a JSONObject
    */
    public OBSScene(JSONObject data) {
        name = data.getString("name");
        items = new ArrayList<>();
        JSONArray sceneItems = data.getJSONArray("sources");
        for (Object __dummyForeachVar0 : sceneItems)
        {
            JSONObject item = (JSONObject)__dummyForeachVar0;
            items.add(new SceneItem(item));
        }
    }

}


