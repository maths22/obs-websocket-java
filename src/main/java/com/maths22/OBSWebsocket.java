package com.maths22;

import com.maths22.callbacks.*;
import com.maths22.data.*;
import com.maths22.exceptions.AuthFailureException;
import com.maths22.exceptions.ErrorResponseException;
import com.maths22.support.EventHandler;
import com.maths22.support.__MultiEventHandler;
import org.glassfish.tyrus.client.ClientManager;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/**
* Instance of a connection with an obs-websocket server
*/
@ClientEndpoint
public class OBSWebsocket
{

    /**
    * Triggered when switching to another scene
    */
    final private SceneChangeCallback SceneChanged = new __MultiSceneChangeCallback();
    /**
    * Triggered when a scene is created, deleted or renamed
    */
    final private EventHandler<Void> SceneListChanged = new __MultiEventHandler<>();
    /**
    * Triggered when the scene item list of the specified scene is reordered
    */
    final private SourceOrderChangeCallback SourceOrderChanged = new __MultiSourceOrderChangeCallback();
    /**
    * Triggered when a new item is added to the item list of the specified scene
    */
    final private SceneItemUpdateCallback SceneItemAdded = new __MultiSceneItemUpdateCallback();
    /**
    * Triggered when an item is removed from the item list of the specified scene
    */
    final private SceneItemUpdateCallback SceneItemRemoved = new __MultiSceneItemUpdateCallback();
    /**
    * Triggered when the visibility of a scene item changes
    */
    final private SceneItemUpdateCallback SceneItemVisibilityChanged = new __MultiSceneItemUpdateCallback();
    /**
    * Triggered when switching to another scene collection
    */
    final private EventHandler<Void> SceneCollectionChanged = new __MultiEventHandler<>();
    /**
    * Triggered when a scene collection is created, deleted or renamed
    */
    final private EventHandler<Void> SceneCollectionListChanged = new __MultiEventHandler<>();
    /**
    * Triggered when switching to another transition
    */
    final private TransitionChangeCallback TransitionChanged = new __MultiTransitionChangeCallback();
    /**
    * Triggered when the current transition duration is changed
    */
    final private TransitionDurationChangeCallback TransitionDurationChanged = new __MultiTransitionDurationChangeCallback();
    /**
    * Triggered when a transition is created or removed
    */
    final private EventHandler<Void> TransitionListChanged = new __MultiEventHandler<>();
    /**
    * Triggered when a transition between two scenes starts. Followed by 
    *  {@link #SceneChanged}
    */
    final private EventHandler<Void> TransitionBegin = new __MultiEventHandler<>();
    /**
    * Triggered when switching to another profile
    */
    final private EventHandler<Void> ProfileChanged = new __MultiEventHandler<>();
    /**
    * Triggered when a profile is created, imported, removed or renamed
    */
    final private EventHandler<Void> ProfileListChanged = new __MultiEventHandler<>();
    /**
    * Triggered when the streaming output state changes
    */
    final private OutputStateCallback StreamingStateChanged = new __MultiOutputStateCallback();
    /**
    * Triggered when the recording output state changes
    */
    final private OutputStateCallback RecordingStateChanged = new __MultiOutputStateCallback();
    /**
    * Triggered when state of the replay buffer changes
    */
    final private OutputStateCallback ReplayBufferStateChanged = new __MultiOutputStateCallback();
    /**
    * Triggered every 2 seconds while streaming is active
    */
    final private StreamStatusCallback StreamStatus = new __MultiStreamStatusCallback();
    /**
    * Triggered when the preview scene selection changes (Studio Mode only)
    */
    final private SceneChangeCallback PreviewSceneChanged = new __MultiSceneChangeCallback();
    /**
    * Triggered when Studio Mode is turned on or off
    */
    final private StudioModeChangeCallback StudioModeSwitched = new __MultiStudioModeChangeCallback();
    /**
    * Triggered when OBS exits
    */
    final private EventHandler<Void> OBSExit = new __MultiEventHandler<>();
    /**
    * Triggered when connected successfully to an obs-websocket server
    */
    final private EventHandler<Void> Connected = new __MultiEventHandler<>();
    /**
    * Triggered when disconnected from an obs-websocket server
    */
    final private EventHandler<CloseReason> Disconnected = new __MultiEventHandler<>();

    public SceneChangeCallback getSceneChanged() {
        return SceneChanged;
    }

    public EventHandler<Void> getSceneListChanged() {
        return SceneListChanged;
    }

    public SourceOrderChangeCallback getSourceOrderChanged() {
        return SourceOrderChanged;
    }

    public SceneItemUpdateCallback getSceneItemAdded() {
        return SceneItemAdded;
    }

    public SceneItemUpdateCallback getSceneItemRemoved() {
        return SceneItemRemoved;
    }

    public SceneItemUpdateCallback getSceneItemVisibilityChanged() {
        return SceneItemVisibilityChanged;
    }

    public EventHandler<Void> getSceneCollectionChanged() {
        return SceneCollectionChanged;
    }

    public EventHandler<Void> getSceneCollectionListChanged() {
        return SceneCollectionListChanged;
    }

    public TransitionChangeCallback getTransitionChanged() {
        return TransitionChanged;
    }

    public TransitionDurationChangeCallback getTransitionDurationChanged() {
        return TransitionDurationChanged;
    }

    public EventHandler<Void> getTransitionListChanged() {
        return TransitionListChanged;
    }

    public EventHandler<Void> getTransitionBegin() {
        return TransitionBegin;
    }

    public EventHandler<Void> getProfileChanged() {
        return ProfileChanged;
    }

    public EventHandler<Void> getProfileListChanged() {
        return ProfileListChanged;
    }

    public OutputStateCallback getStreamingStateChanged() {
        return StreamingStateChanged;
    }

    public OutputStateCallback getRecordingStateChanged() {
        return RecordingStateChanged;
    }

    public OutputStateCallback getReplayBufferStateChanged() {
        return ReplayBufferStateChanged;
    }

    public StreamStatusCallback getStreamStatus() {
        return StreamStatus;
    }

    public SceneChangeCallback getPreviewSceneChanged() {
        return PreviewSceneChanged;
    }

    public StudioModeChangeCallback getStudioModeSwitched() {
        return StudioModeSwitched;
    }

    public EventHandler<Void> getOBSExit() {
        return OBSExit;
    }

    public EventHandler<Void> getConnected() {
        return Connected;
    }

    public EventHandler<CloseReason> getDisconnected() {
        return Disconnected;
    }

    /**
    * WebSocket request timeout, represented as a TimeSpan object
    */
    public long getWSTimeout() {
        if (getWSConnection() != null)
            return getWSConnection().getMaxIdleTimeout();
        else
            return wsTimeout;
    }

    public void setWSTimeout(long value) {
        wsTimeout = value;
        if (getWSConnection() != null)
            getWSConnection().setMaxIdleTimeout(wsTimeout);
         
    }

    private long wsTimeout = 0;
    /**
    * Current connection state
    */
    public boolean getIsConnected() {
        return (getWSConnection() != null && getWSConnection().isOpen());
    }

    /**
    * Underlying WebSocket connection to an obs-websocket server. Value is null when disconnected.
    */
    private Session wsConnection;
    public Session getWSConnection() {
        return wsConnection;
    }

    public void setWSConnection(Session value) {
        wsConnection = value;
    }

    final private Map<String, CompletableFuture<JSONObject>> responseHandlers;
    /**
    * Constructor
    */
    public OBSWebsocket() {
        responseHandlers = new HashMap<>();
    }

    /**
    * Connect this instance to the specified URL, and authenticate (if needed) with the specified password
    * 
    *  @param url Server URL in standard URL format
    *  @param password Server password
    */
    public void connect(String url, String password) throws URISyntaxException, IOException, DeploymentException, AuthFailureException {
        if (getWSConnection() != null && getWSConnection().isOpen())
            disconnect();

        WebSocketContainer wsContainer = ClientManager.createClient();
        wsContainer.setDefaultMaxSessionIdleTimeout(wsTimeout);
        setWSConnection(wsContainer.connectToServer(this, new URI(url)));

        OBSAuthInfo authInfo = getAuthInfo();
        if (authInfo.isAuthRequired())
            authenticate(password,authInfo);

        Connected.invoke(this);
         
    }

    @OnClose
    public void onClose(CloseReason e, Session session) {
        Disconnected.invoke(this, e);
    }

    /**
    * Disconnect this instance from the server
    */
    public void disconnect() {
        if (getWSConnection() != null) {
            try {
                getWSConnection().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        setWSConnection(null);
        for (CompletableFuture<JSONObject> cb : responseHandlers.values())
        {
            cb.cancel(true);
        }
    }

    // This callback handles incoming JSON messages and determines if it's
    // a request response or an event ("Update" in obs-websocket terminology)
    @OnMessage
    public void websocketMessageHandler(String message, Session session) {
        JSONObject body = new JSONObject(message);
        if (body.has("message-id"))
        {
            // Handle a request :
            // Find the response handler based on
            // its associated message ID
            String msgID = body.getString("message-id");
            CompletableFuture<JSONObject> handler = responseHandlers.get(msgID);
            if (handler != null)
            {
                // Set the response body as Result and notify the request sender
                handler.complete(body);
                // The message with the given ID has been processed,
                // so its handler can be discarded
                responseHandlers.remove(msgID);
            }
             
        }
        else if (body.has("update-type"))
        {
            // Handle an event
            String eventType = body.getString("update-type");
            processEventType(eventType,body);
        }
          
    }

    public JSONObject sendRequest(String requestType) {
        return sendRequest(requestType, null);
    }

    /**
    * Sends a message to the websocket API with the specified request type and optional parameters
    * 
    *  @param requestType obs-websocket request type, must be one specified in the protocol specification
    *  @param additionalFields additional JSON fields if required by the request type
    *  @return The server's JSON response as a JSONObject
    */
    public JSONObject sendRequest(String requestType, JSONObject additionalFields) throws ErrorResponseException {
        String messageID;
        do
        {
            // Generate a random message id and make sure it is unique within the handlers dictionary
            messageID = newMessageID();
        }
        while (responseHandlers.get(messageID) != null);
        // Build the bare-minimum body for a request
        JSONObject body = new JSONObject();
        body.put("request-type", requestType);
        body.put("message-id", messageID);
        // Add optional fields if provided
        if (additionalFields != null)
        {
            body = mergeJson(body, additionalFields);
        }
         
        // Prepare the asynchronous response handler
        CompletableFuture<JSONObject> tcs = new CompletableFuture<>();
        responseHandlers.put(messageID, tcs);
        // Send the message and wait for a response
        // (received and notified by the websocket response handler)
        getWSConnection().getAsyncRemote().sendText(body.toString());

         
        // Throw an exception if the server returned an error.
        // An error occurs if authentication fails or one if the request body is invalid.
        JSONObject result;
        try {
            result = tcs.get();
            if (tcs.isCancelled())
                throw new ErrorResponseException("Request canceled");

            if (tcs.isCompletedExceptionally())
                throw new ErrorResponseException("An unknown error occured");

            if ("error".equals(result.getString("status")))
                throw new ErrorResponseException(result.getString("error"));
        } catch (CancellationException e) {
            throw new ErrorResponseException("Request canceled");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new ErrorResponseException("An unknown error occured");
        }


        return result;
    }

    /**
    * Requests version info regarding obs-websocket, the API and OBS Studio
    * 
    *  @return Version info in an OBSVersion
    *  object
    */
    public OBSVersion getVersion() {
        JSONObject response = sendRequest("GetVersion");
        return new OBSVersion(response);
    }

    /**
    * Request authentication data. You don't have to call this manually.
    * 
    *  @return Authentication data in an OBSAuthInfo
    *  object
    */
    public OBSAuthInfo getAuthInfo() {
        JSONObject response = sendRequest("GetAuthRequired");
        return new OBSAuthInfo(response);
    }

    /**
    * Authenticates to the Websocket server using the challenge and salt given in the passed
    *  OBSAuthInfo object
    * 
    *  @param password User password
    *  @param authInfo Authentication data
    *  @return true if authentication succeeds, false otherwise
    */
    public boolean authenticate(String password, OBSAuthInfo authInfo) throws AuthFailureException {
        String secret = hashEncode(password + authInfo.getPasswordSalt());
        String authResponse = hashEncode(secret + authInfo.getChallenge());
        JSONObject requestFields = new JSONObject();
        requestFields.put("auth", authResponse);
        try
        {
            // Throws ErrorResponseException if auth fails
            sendRequest("Authenticate", requestFields);
        }
        catch (ErrorResponseException ex)
        {
            throw new AuthFailureException();
        }

        return true;
    }

    /**
    * Update message handler
    * 
    *  @param eventType Value of "event-type" in the JSON body
    *  @param body full JSON message body
    */
    protected void processEventType(String eventType, JSONObject body) {
        StreamStatus status;
        switch (eventType) {
            case "SwitchScenes":
                SceneChanged.invoke(this, body.getString("scene-name"));

                break;
            case "ScenesChanged":
                SceneListChanged.invoke(this);

                break;
            case "SourceOrderChanged":
                SourceOrderChanged.invoke(this, body.getString("scene-name"));

                break;
            case "SceneItemAdded":
                SceneItemAdded.invoke(this, body.getString("scene-name"), body.getString("item-name"));

                break;
            case "SceneItemRemoved":
                SceneItemRemoved.invoke(this, body.getString("scene-name"), body.getString("item-name"));

                break;
            case "SceneItemVisibilityChanged":
                SceneItemVisibilityChanged.invoke(this, body.getString("scene-name"), body.getString("item-name"));

                break;
            case "SceneCollectionChanged":
                SceneCollectionChanged.invoke(this);

                break;
            case "SceneCollectionListChanged":
                SceneCollectionListChanged.invoke(this);

                break;
            case "SwitchTransition":
                TransitionChanged.invoke(this, body.getString("transition-name"));

                break;
            case "TransitionDurationChanged":
                TransitionDurationChanged.invoke(this, body.getInt("new-duration"));

                break;
            case "TransitionListChanged":
                TransitionListChanged.invoke(this);

                break;
            case "TransitionBegin":
                TransitionBegin.invoke(this);

                break;
            case "ProfileChanged":
                ProfileChanged.invoke(this);

                break;
            case "ProfileListChanged":
                ProfileListChanged.invoke(this);

                break;
            case "StreamStarting":
                StreamingStateChanged.invoke(this, OutputState.Starting);

                break;
            case "StreamStarted":
                StreamingStateChanged.invoke(this, OutputState.Started);

                break;
            case "StreamStopping":
                StreamingStateChanged.invoke(this, OutputState.Stopping);

                break;
            case "StreamStopped":
                StreamingStateChanged.invoke(this, OutputState.Stopped);

                break;
            case "RecordingStarting":
                RecordingStateChanged.invoke(this, OutputState.Starting);

                break;
            case "RecordingStarted":
                RecordingStateChanged.invoke(this, OutputState.Started);

                break;
            case "RecordingStopping":
                RecordingStateChanged.invoke(this, OutputState.Stopping);

                break;
            case "RecordingStopped":
                RecordingStateChanged.invoke(this, OutputState.Stopped);

                break;
            case "StreamStatus":
                status = new StreamStatus(body);
                StreamStatus.invoke(this, status);

                break;
            case "PreviewSceneChanged":
                PreviewSceneChanged.invoke(this, body.getString("scene-name"));

                break;
            case "StudioModeSwitched":
                StudioModeSwitched.invoke(this, body.getBoolean("new-state"));

                break;
            case "ReplayStarting":
                ReplayBufferStateChanged.invoke(this, OutputState.Starting);

                break;
            case "ReplayStarted":
                ReplayBufferStateChanged.invoke(this, OutputState.Started);

                break;
            case "ReplayStopping":
                ReplayBufferStateChanged.invoke(this, OutputState.Stopping);

                break;
            case "ReplayStopped":
                ReplayBufferStateChanged.invoke(this, OutputState.Stopped);

                break;
            case "Exiting":
                OBSExit.invoke(this);

                break;
        }
                                      
    }

    /**
    * Encode a Base64-encoded SHA-256 hash
    * 
    *  @param input source string
    *  @return
    */
    protected String hashEncode(String input) {
        MessageDigest sha256;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] textBytes = input.getBytes();
        byte[] hash = sha256.digest(textBytes);
        return Base64.getEncoder().encodeToString(hash);
    }


    protected String newMessageID() {
        return newMessageID(16);
    }
    
    /**
    * Generate a message ID
    * 
    *  @param length (optional) message ID length
    *  @return A random string of alphanumerical characters
    */
    protected String newMessageID(int length) {
        final String pool = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0;i < length;i++)
        {
            int index = random.nextInt(pool.length() - 1);
            result.append(pool.charAt(index));
        }
        return result.toString();
    }

    /**
    * Get the current scene info along with its items
    * 
    *  @return An OBSScene
    *  object describing the current scene
    */
    public OBSScene getCurrentScene() {
        JSONObject response = sendRequest("GetCurrentScene");
        return new OBSScene(response);
    }

    /**
    * Set the current scene to the specified one
    * 
    *  @param sceneName The desired scene name
    */
    public void setCurrentScene(String sceneName) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("scene-name", sceneName);
        sendRequest("SetCurrentScene", requestFields);
    }

    /**
    * List every available scene
    * 
    *  @return A List of OBSScene objects describing each scene
    */
    public List<OBSScene> listScenes() {
        JSONObject response = sendRequest("GetSceneList");
        JSONArray items = response.getJSONArray("scenes");
        List<OBSScene> scenes = new ArrayList<>();
        for (Object __dummyForeachVar0 : items)
        {
            JSONObject sceneData = (JSONObject)__dummyForeachVar0;
            OBSScene scene = new OBSScene(sceneData);
            scenes.add(scene);
        }
        return scenes;
    }

    /**
    * Change the visibility of the specified scene item
    * 
    *  @param itemName Scene item which visiblity will be changed
    *  @param visible Desired visiblity
    *  @param sceneName Scene name of the specified item
    */
    public void setSourceRender(String itemName, boolean visible, String sceneName) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("source", itemName);
        requestFields.put("render", visible);
        if (sceneName != null)
            requestFields.put("scene-name", sceneName);

        sendRequest("SetSourceRender", requestFields);
    }

    /**
    * Start/Stop the streaming output
    */
    public void toggleStreaming() {
        sendRequest("StartStopStreaming");
    }

    /**
    * Start/Stop the recording output
    */
    public void toggleRecording() {
        sendRequest("StartStopRecording");
    }

    /**
    * Get the current status of the streaming and recording outputs
    * 
    *  @return An OutputStatus
    *  object describing the current outputs states
    */
    public OutputStatus getStreamingStatus() {
        JSONObject response = sendRequest("GetStreamingStatus");
        return new OutputStatus(response);
    }

    /**
    * List all transitions
    * 
    *  @return A List
    *  of all transition names
    */
    public List<String> listTransitions() {
        JSONObject response = sendRequest("GetTransitionList");
        JSONArray items = response.getJSONArray("transitions");
        List<String> transitionNames = new ArrayList<>();
        for (Object __dummyForeachVar1 : items)
        {
            JSONObject item = (JSONObject)__dummyForeachVar1;
            transitionNames.add(item.getString("name"));
        }
        return transitionNames;
    }

    /**
    * Get the current transition name and duration
    * 
    *  @return A TransitionSettings
    *  object with the current transition name and duration
    */
    public TransitionSettings getCurrentTransition() {
        JSONObject respBody = sendRequest("GetCurrentTransition");
        return new TransitionSettings(respBody);
    }

    /**
    * Set the current transition to the specified one
    * 
    *  @param transitionName Desired transition name
    */
    public void setCurrentTransition(String transitionName) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("transition-name", transitionName);
        sendRequest("SetCurrentTransition", requestFields);
    }

    /**
    * Change the transition's duration
    * 
    *  @param duration Desired transition duration (in milliseconds)
    */
    public void setTransitionDuration(int duration) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("duration", duration);
        sendRequest("SetTransitionDuration", requestFields);
    }

    /**
    * Change the volume of the specified source
    * 
    *  @param sourceName Name of the source which volume will be changed
    *  @param volume Desired volume in linear scale (0.0 to 1.0)
    */
    public void setVolume(String sourceName, float volume) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("source", sourceName);
        requestFields.put("volume", volume);
        sendRequest("SetVolume", requestFields);
    }

    /**
    * Get the volume of the specified source
    * 
    *  @param sourceName Source name
    *  @return A VolumeInfo
    *  object containing the volume and mute state of the specified source
    */
    public VolumeInfo getVolume(String sourceName) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("source", sourceName);
        JSONObject response = sendRequest("GetVolume", requestFields);
        return new VolumeInfo(response);
    }

    /**
    * Set the mute state of the specified source
    * 
    *  @param sourceName Name of the source which mute state will be changed
    *  @param mute Desired mute state
    */
    public void setMute(String sourceName, boolean mute) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("source", sourceName);
        requestFields.put("mute", mute);
        sendRequest("SetMute", requestFields);
    }

    /**
    * Toggle the mute state of the specified source
    * 
    *  @param sourceName Name of the source which mute state will be toggled
    */
    public void toggleMute(String sourceName) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("source", sourceName);
        sendRequest("ToggleMute", requestFields);
    }

    /**
    * Set the position of the specified scene item
    * 
    *  @param itemName Name of the scene item which position will be changed
    *  @param x X coordinate
    *  @param y Y coordinate
    *  @param sceneName (optional) name of the scene the item belongs to
    */
    public void setSceneItemPosition(String itemName, float x, float y, String sceneName) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("item", itemName);
        requestFields.put("x", x);
        requestFields.put("y", y);
        if (sceneName != null)
            requestFields.put("scene-name", sceneName);
         
        sendRequest("SetSceneItemPosition", requestFields);
    }

    /**
    * Set the scale and rotation of the specified scene item
    * 
    *  @param itemName Name of the scene item which transform will be changed
    *  @param rotation Rotation in Degrees
    *  @param xScale Horizontal scale factor
    *  @param yScale Vertical scale factor
    *  @param sceneName (optional) name of the scene the item belongs to
    */
    public void setSceneItemTransform(String itemName, float rotation, float xScale, float yScale, String sceneName) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("item", itemName);
        requestFields.put("x-scale", xScale);
        requestFields.put("y-scale", yScale);
        requestFields.put("rotation", rotation);
        if (sceneName != null)
            requestFields.put("scene-name", sceneName);
         
        sendRequest("SetSceneItemTransform", requestFields);
    }

    /**
    * Set the current scene collection to the specified one
    * 
    *  @param scName Desired scene collection name
    */
    public void setCurrentSceneCollection(String scName) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("sc-name", scName);
        sendRequest("SetCurrentSceneCollection", requestFields);
    }

    /**
    * Get the name of the current scene collection
    * 
    *  @return Name of the current scene collection
    */
    public String getCurrentSceneCollection() {
        JSONObject response = sendRequest("GetCurrentSceneCollection");
        return response.getString("sc-name");
    }

    /**
    * List all scene collections
    * 
    *  @return A List
    *  of the names of all scene collections
    */
    public List<String> listSceneCollections() {
        JSONObject response = sendRequest("ListSceneCollections");
        JSONArray items = response.getJSONArray("scene-collections");
        List<String> sceneCollections = new ArrayList<>();
        for (Object __dummyForeachVar2 : items)
        {
            JSONObject item = (JSONObject)__dummyForeachVar2;
            sceneCollections.add(item.getString("sc-name"));
        }
        return sceneCollections;
    }

    /**
    * Set the current profile to the specified one
    * 
    *  @param profileName Name of the desired profile
    */
    public void setCurrentProfile(String profileName) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("profile-name", profileName);
        sendRequest("SetCurrentProfile", requestFields);
    }

    /**
    * Get the name of the current profile
    * 
    *  @return Name of the current profile
    */
    public String getCurrentProfile() {
        JSONObject response = sendRequest("GetCurrentProfile");
        return response.getString("profile-name");
    }

    /**
    * List all profiles
    * 
    *  @return A List
    *  of the names of all profiles
    */
    public List<String> listProfiles() {
        JSONObject response = sendRequest("ListProfiles");
        JSONArray items = response.getJSONArray("profiles");
        List<String> profiles = new ArrayList<>();
        for (Object __dummyForeachVar3 : items)
        {
            JSONObject item = (JSONObject)__dummyForeachVar3;
            profiles.add(item.getString("profile-name"));
        }
        return profiles;
    }

    // TODO: needs updating
    /**
    * Start streaming. Will trigger an error if streaming is already active
    */
    public void startStreaming() {
        sendRequest("StartStreaming");
    }

    /**
    * Stop streaming. Will trigger an error if streaming is not active.
    */
    public void stopStreaming() {
        sendRequest("StopStreaming");
    }

    /**
    * Start recording. Will trigger an error if recording is already active.
    */
    public void startRecording() {
        sendRequest("StartRecording");
    }

    /**
    * Stop recording. Will trigger an error if recording is not active.
    */
    public void stopRecording() {
        sendRequest("StopRecording");
    }

    /**
    * Change the current recording folder
    * 
    *  @param recFolder Recording folder path
    */
    public void setRecordingFolder(String recFolder) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("rec-folder", recFolder);
        sendRequest("SetRecordingFolder");
    }

    /**
    * Get the path of the current recording folder
    * 
    *  @return Current recording folder path
    */
    public String getRecordingFolder() {
        JSONObject response = sendRequest("GetRecordingFolder");
        return response.getString("rec-folder");
    }

    /**
    * Get duration of the currently selected transition (if supported)
    * 
    *  @return Current transition duration (in milliseconds)
    */
    public int getTransitionDuration() {
        JSONObject response = sendRequest("GetTransitionDuration");
        return response.getInt("transition-duration");
    }

    /**
    * Get status of Studio Mode
    * 
    *  @return Studio Mode status (on/off)
    */
    public boolean studioModeEnabled() {
        JSONObject response = sendRequest("GetStudioModeStatus");
        return response.getBoolean("studio-mode");
    }

    /**
    * Enable/disable Studio Mode
    * 
    *  @param enable Desired Studio Mode status
    */
    public void setStudioMode(boolean enable) {
        if (enable)
            sendRequest("EnableStudioMode");
        else
            sendRequest("DisableStudioMode");
    }

    /**
    * Toggle Studio Mode status (on to off or off to on)
    */
    public void toggleStudioMode() {
        sendRequest("ToggleStudioMode");
    }

    /**
    * Get the currently selected preview scene. Triggers an error
    * if Studio Mode is disabled
    * 
    *  @return Preview scene object
    */
    public OBSScene getPreviewScene() {
        JSONObject response = sendRequest("GetPreviewScene");
        return new OBSScene(response);
    }

    /**
    * Change the currently active preview scene to the one specified.
    * Triggers an error if Studio Mode is disabled
    * 
    *  @param previewScene Preview scene name
    */
    public void setPreviewScene(String previewScene) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("scene-name", previewScene);
        sendRequest("SetPreviewScene", requestFields);
    }

    /**
    * Change the currently active preview scene to the one specified.
    * Triggers an error if Studio Mode is disabled.
    * 
    *  @param previewScene Preview scene object
    */
    public void setPreviewScene(OBSScene previewScene) {
        setPreviewScene(previewScene.getName());
    }

    /**
    * Triggers a Studio Mode transition (preview scene to program)
    * 
    *  @param transitionDuration (optional) Transition duration
    *  @param transitionName (optional) Name of transition to use
    */
    public void transitionToProgram(int transitionDuration, String transitionName) {
        JSONObject requestFields = new JSONObject();
        if (transitionDuration > -1 || transitionName != null)
        {
            JSONObject withTransition = new JSONObject();
            if (transitionDuration > -1)
                withTransition.put("duration", transitionDuration);
             
            if (transitionName != null)
                withTransition.put("name", transitionName);
             
            requestFields.put("with-transition", withTransition);
        }

        sendRequest("TransitionToProgram", requestFields);
    }

    /**
    * Get if the specified source is muted
    * 
    *  @param sourceName Source name
    *  @return Source mute status (on/off)
    */
    public boolean getMute(String sourceName) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("source", sourceName);
        JSONObject response = sendRequest("GetMute");
        return response.getBoolean("muted");
    }

    /**
    * Toggle the Replay Buffer on/off
    */
    public void toggleReplayBuffer() {
        sendRequest("StartStopReplayBuffer");
    }

    /**
    * Start recording into the Replay Buffer. Triggers an error
    * if the Replay Buffer is already active, or if the "Save Replay Buffer"
    * hotkey is not set in OBS' settings
    */
    public void startReplayBuffer() {
        sendRequest("StartReplayBuffer");
    }

    /**
    * Stop recording into the Replay Buffer. Triggers an error if the
    * Replay Buffer is not active.
    */
    public void stopReplayBuffer() {
        sendRequest("StopReplayBuffer");
    }

    /**
    * Save and flush the contents of the Replay Buffer to disk. Basically
    * the same as triggering the "Save Replay Buffer" hotkey in OBS.
    * Triggers an error if Replay Buffer is not active.
    */
    public void saveReplayBuffer() {
        sendRequest("SaveReplayBuffer");
    }

    /**
    * Set the audio sync offset of the specified source
    * 
    *  @param sourceName Source name
    *  @param syncOffset Audio offset (in nanoseconds) for the specified source
    */
    public void setSyncOffset(String sourceName, int syncOffset) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("source", sourceName);
        requestFields.put("offset", syncOffset);
        sendRequest("SetSyncOffset", requestFields);
    }

    /**
    * Get the audio sync offset of the specified source
    * 
    *  @param sourceName Source name
    *  @return Audio offset (in nanoseconds) of the specified source
    */
    public int getSyncOffset(String sourceName) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("source", sourceName);
        JSONObject response = sendRequest("GetSyncOffset", requestFields);
        return response.getInt("offset");
    }

    /**
    * Set the relative crop coordinates of the specified source item
    * 
    *  @param sceneItemName Name of the scene item
    *  @param cropInfo Crop coordinates
    *  @param sceneName (optional) parent scene name of the specified source
    */
    public void setSceneItemCrop(String sceneItemName, SceneItemCropInfo cropInfo, String sceneName) {
        JSONObject requestFields = new JSONObject();
        if (sceneName != null)
            requestFields.put("scene-name", sceneName);
         
        requestFields.put("item", sceneItemName);
        requestFields.put("top", cropInfo.getTop());
        requestFields.put("bottom", cropInfo.getBottom());
        requestFields.put("left", cropInfo.getLeft());
        requestFields.put("right", cropInfo.getRight());
        sendRequest("SetSceneItemCrop", requestFields);
    }

    /**
    * Set the relative crop coordinates of the specified source item
    * 
    *  @param sceneItem Scene item object
    *  @param cropInfo Crop coordinates
    *  @param scene Parent scene of scene item
    */
    public void setSceneItemCrop(SceneItem sceneItem, SceneItemCropInfo cropInfo, OBSScene scene) {
        setSceneItemCrop(sceneItem.getSourceName(),cropInfo,scene.getName());
    }

    /**
    * Get names of configured special sources (like Desktop Audio
    * and Mic sources)
    * 
    *  @return
    */
    public Map<String, String> getSpecialSources() {
        JSONObject response = sendRequest("GetSpecialSources");
        Map<String, String> sources = new HashMap<>();
        for (Map.Entry<String, Object> entry : response.toMap().entrySet())
        {
            String key = entry.getKey();
            String value = (String) entry.getValue();
            if (!"request-type".equals(key) && !"message-id".equals(key))
            {
                sources.put(key, value);
            }
             
        }
        return sources;
    }

    /**
    * Set current streaming settings
    * 
    *  @param service 
    *  @param save
    */
    public void setStreamingSettings(StreamingService service, boolean save) {
        JSONObject requestFields = new JSONObject();
        requestFields.put("type", service.getType());
        requestFields.put("settings", service.getSettings());
        requestFields.put("save", save);
        sendRequest("SetStreamSettings", requestFields);
    }

    /**
    * Get current streaming settings
    * 
    *  @return
    */
    public StreamingService getStreamSettings() {
        JSONObject response = sendRequest("GetStreamSettings");
        StreamingService service = new StreamingService();
        service.setType(response.getString("type"));
        service.setSettings(response.getJSONObject("settings"));
        return service;
    }

    /**
    * Save current Streaming settings to disk
    */
    public void saveStreamSettings() {
        sendRequest("SaveStreamSettings");
    }

    /**
    * Get settings of the specified BrowserSource
    * 
    *  @param sourceName Source name
    *  @param sceneName Optional name of a scene where the specified source can be found
    *  @return BrowserSource properties
    */
    public BrowserSourceProperties getBrowserSourceProperties(String sourceName, String sceneName) {
        JSONObject request = new JSONObject();
        request.put("source", sourceName);
        if (sceneName != null)
            request.put("scene-name", sourceName);

        JSONObject response = sendRequest("GetBrowserSourceProperties", request);
        return new BrowserSourceProperties(response);
    }

    /**
    * Set settings of the specified BrowserSource
    * 
    *  @param sourceName Source name
    *  @param props BrowserSource properties
    *  @param sceneName Optional name of a scene where the specified source can be found
    */
    public void setBrowserSourceProperties(String sourceName, BrowserSourceProperties props, String sceneName) {
        JSONObject request = new JSONObject();
        request.put("source", sourceName);
        if (sceneName != null)
            request.put("scene-name", sourceName);
         
        request = mergeJson(request, props.toJSON());
        sendRequest("SetBrowserSourceProperties", request);
    }

    private JSONObject mergeJson(JSONObject o1, JSONObject o2) {
        JSONObject mergedObj = new JSONObject();

        Iterator i1 = o1.keys();
        Iterator i2 = o2.keys();
        String tmp_key;
        while(i1.hasNext()) {
            tmp_key = (String) i1.next();
            mergedObj.put(tmp_key, o1.get(tmp_key));
        }
        while(i2.hasNext()) {
            tmp_key = (String) i2.next();
            mergedObj.put(tmp_key, o2.get(tmp_key));
        }
        return mergedObj;
    }

}


