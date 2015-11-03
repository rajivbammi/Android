package com.codepath.apps.restclienttemplate.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.Activities.ProfileActivity;
import com.codepath.apps.restclienttemplate.Adapter.TweetsArrayAdapter;
import com.codepath.apps.restclienttemplate.Models.Tweet;
import com.codepath.apps.restclienttemplate.Models.User;
import com.codepath.apps.restclienttemplate.TwitterApplication;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

/**
 * Created by rbammi on 11/1/15.
 */
public class HomeTimelineFragment extends TweetsListFragment {
    private TwitterClient client;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TweetsArrayAdapter adapter = getAdapter();
        adapter.setProfileImageListener(new TweetsArrayAdapter.ProfileImageListener() {
            @Override
            public void onProfileImgSelected(User user) {
                Intent i = new Intent(getContext(), ProfileActivity.class);
                i.putExtra("user", user);
                Toast.makeText(getContext(), "Event fired", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        client = TwitterApplication.getRestClient();
        populateTimeLine(0);
    }

    public void populateTimeLine(long since_id) {
        client.getHomeTimeline(since_id, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
               addAll(Tweet.fromJsonArray(response));
                //swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                //super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i("DEBUG", "ERROR" + errorResponse.toString());
                //swipeContainer.setRefreshing(false);
            }
        });
    }
}
