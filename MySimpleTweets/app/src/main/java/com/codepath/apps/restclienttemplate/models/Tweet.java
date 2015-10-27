package com.codepath.apps.restclienttemplate.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rbammi on 10/23/15.
 */
public class Tweet {
    private String body;
    private Long uid;
    private User user;
    private String createdAt;
    private static long max_id;

    public String getBody() {
        return body;
    }

    public User getUser() {
        return user;
    }

    public Long getUid() {
        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public static Tweet fromJson(JSONObject jsonObject) {
        Tweet tweet = new Tweet();
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");;
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tweet;
    }

    public static ArrayList<Tweet> fromJsonArray(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i< jsonArray.length(); i++) {
            try {
              JSONObject jsonObject =  jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJson(jsonObject);
                if (tweet != null) {
                    tweets.add(tweet);
                    max_id = tweet.getUid();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        return tweets;
    }

    public static long getMaxId() {
        return max_id;
    }
}
