package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rbammi on 10/23/15.
 */
public class User {
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;
    
    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public static User fromJson(JSONObject jsonObject) {
        User u = new User();

        try {
            u.uid = jsonObject.getLong("id");
            u.name = jsonObject.getString("name");
            u.screenName = jsonObject.getString("screen_name");
            u.profileImageUrl = jsonObject.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return u;
    }
}
