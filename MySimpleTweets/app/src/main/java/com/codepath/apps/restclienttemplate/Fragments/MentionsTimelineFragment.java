package com.codepath.apps.restclienttemplate.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.Activities.ProfileActivity;
import com.codepath.apps.restclienttemplate.Adapter.TweetsArrayAdapter;
import com.codepath.apps.restclienttemplate.Models.Tweet;
import com.codepath.apps.restclienttemplate.Models.User;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApplication;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

/**
 * Created by rbammi on 11/2/15.
 */
public class MentionsTimelineFragment extends TweetsListFragment {
    private TwitterClient client;
    private SwipeRefreshLayout swipeContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();

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
        populateTimeLine(0);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        swipeContainer= (SwipeRefreshLayout) view.findViewById(R.id.SwipeContainer);
        //Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("DEBUG", "inside refresh of MentionsTimelineFragment");
                getAdapter().clear();
                populateTimeLine(0);
            }
        });
    }

    public void populateTimeLine(long since_id) {
        client.getMentionsTimeline(new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                addAll(Tweet.fromJsonArray(response));
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.i("DEBUG", "ERROR" + errorResponse.toString());
                swipeContainer.setRefreshing(false);
            }
        });
    }
}
