package com.example.rbammi.simplechat;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by rbammi on 10/22/15.
 */

@ParseClassName("Message")

public class Message extends ParseObject {
    public String getUserId() {
        return getString("userId");
    }

    public String getBody() {
        return getString("body");
    }

    public void setUserId(String userId) {
        put("userId", userId);
    }

    public void setBody(String body) {
        put("body", body);
    }
}
