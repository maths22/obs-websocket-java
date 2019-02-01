package com.maths22.data;

import org.json.JSONObject;

/**
* BrowserSource source properties
*/
public class BrowserSourceProperties   
{
    public BrowserSourceProperties() {
    }

    /**
    * URL to load in the embedded browser
    */
    private String url = "";
    /**
    * true if the URL points to a local file, false otherwise.
    */
    private boolean isLocalFile = false;
    /**
    * Additional CSS to apply to the page
    */
    private String customCSS = "";
    /**
    * Embedded browser render (viewport) width
    */
    private int width = 0;
    /**
    * Embedded browser render (viewport) height
    */
    private int height = 0;
    /**
    * Embedded browser render frames per second
    */
    private int fps = 0;
    /**
    * true if source should be disabled (inactive) when not visible, false otherwise
    */
    private boolean shutdownWhenNotVisible = false;
    /**
    * true if source should be visible, false otherwise
    */
    private boolean visible = false;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isLocalFile() {
        return isLocalFile;
    }

    public void setLocalFile(boolean localFile) {
        isLocalFile = localFile;
    }

    public String getCustomCSS() {
        return customCSS;
    }

    public void setCustomCSS(String customCSS) {
        this.customCSS = customCSS;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public boolean isShutdownWhenNotVisible() {
        return shutdownWhenNotVisible;
    }

    public void setShutdownWhenNotVisible(boolean shutdownWhenNotVisible) {
        this.shutdownWhenNotVisible = shutdownWhenNotVisible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
    * Construct the object from JSON response data
    * 
    *  @param props
    */
    public BrowserSourceProperties(JSONObject props) {
        url = props.getString("url");
        isLocalFile = props.getBoolean("is_local_file");
        customCSS = props.getString("css");
        width = props.getInt("width");
        height = props.getInt("height");
        fps = props.getInt("fps");
        shutdownWhenNotVisible = props.getBoolean("shutdown");
        visible = props.getBoolean("render");
    }

    /**
    * Convert the object back to JSON
    * 
    *  @return
    */
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("url", url);
        obj.put("is_local_file", isLocalFile);
        obj.put("css", customCSS);
        obj.put("width", width);
        obj.put("height", height);
        obj.put("fps", fps);
        obj.put("shutdown", shutdownWhenNotVisible);
        obj.put("render", visible);
        return obj;
    }

}


