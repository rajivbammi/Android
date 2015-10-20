package com.example.rbammi.gridimagesearch.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rbammi on 10/15/15.
 */
public class ImageResult implements Serializable {
    private String imgUrl;
    private String thumbUrl;
    private String title;

    public String getImgUrl() {
        return imgUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public String getTitle() {
        return title;
    }

    public ImageResult(JSONObject json) {
        try {
            this.imgUrl = json.getString("url");
            this.thumbUrl = json.getString("tbUrl");
            this.title = json.getString("title");

        } catch (JSONException e) {
             e.printStackTrace();
        }
    }

    public static ArrayList<ImageResult> fromJsonArray(JSONArray jArray) {
        ArrayList<ImageResult> imgArrList = new ArrayList<ImageResult>();
        for(int i = 0; i< jArray.length(); i++) {
            try {
                ImageResult img = new ImageResult(jArray.getJSONObject(i));
                Log.i("Debug", img.toString());
                imgArrList.add(img);
            } catch (JSONException e) {
                e.getStackTrace();
            }
        }
        Log.i("Debug", imgArrList.toString());
        return imgArrList;
    }
}
