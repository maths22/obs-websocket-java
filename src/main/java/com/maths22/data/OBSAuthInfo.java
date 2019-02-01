package com.maths22.data;

import org.json.JSONObject;

/**
* Data required by authentication
*/
public class OBSAuthInfo   
{
    /**
    * True if authentication is required, false otherwise
    */
    private final boolean authRequired;
    /**
    * Authentication challenge
    */
    private final String challenge;
    /**
    * Password salt
    */
    private final String passwordSalt;

    public boolean isAuthRequired() {
        return authRequired;
    }

    public String getChallenge() {
        return challenge;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    /**
    * Builds the object from JSON response body
    * 
    *  @param data JSON response body as a JSONObject
    */
    public OBSAuthInfo(JSONObject data) {
        authRequired = data.getBoolean("authRequired");
        challenge = data.has("challenge") ? data.getString("challenge") : null;
        passwordSalt = data.has("salt") ? data.getString("salt") : null;
    }

}


