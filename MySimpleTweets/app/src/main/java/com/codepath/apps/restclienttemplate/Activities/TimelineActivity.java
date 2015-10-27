package com.codepath.apps.restclienttemplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.codepath.apps.restclienttemplate.Listener.EndlessScrollListener;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.Adapter.TweetsArrayAdapter;
import com.codepath.apps.restclienttemplate.TwitterApplication;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.codepath.apps.restclienttemplate.Models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;

public class TimelineActivity extends AppCompatActivity {

    private TwitterClient client;
    private TweetsArrayAdapter aTweets;
    private ArrayList<Tweet> tweets;
    private ListView lvTweets;
    private int REQUEST_CODE_COMPOSE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        setUp();
        populateTimeLine(0);
    }

    public void setUp() {
        client = TwitterApplication.getRestClient();
        lvTweets = (ListView) findViewById(R.id.lvTimeline);
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                //customLoadMoreDataFromApi(page);
                long max_id = Tweet.getMaxId() -1;
                Log.i("DEBUG", "Inside load more...." + max_id);
                Log.i("DEBUG", "Inside load more...." + tweets.size());
                Log.i("DEBUG", "Inside load more...." + tweets);

                populateTimeLine(max_id);
                return true; // ONLY if more data is actually being loaded; false otherwise.
            }
        });

        tweets = new ArrayList<>();
        aTweets = new TweetsArrayAdapter(this, tweets);
        lvTweets.setAdapter(aTweets);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.compose_menu, menu);
        return true;
    }

    public void populateTimeLine(long since_id) {
        client.getHomeTimelines(since_id, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //super.onSuccess(statusCode, headers, response);
                    //Log.i("Debug", "response: " + response.toString());
                    //Log.i("Debug", "length: " + response.length());
                    aTweets.addAll(Tweet.fromJsonArray(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                //super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i("DEBUG", errorResponse.toString());
            }
        });
    }

    public void onComposeAction(MenuItem item) {
        Log.i("DEBUG", "Composing message...");
        Intent i = new Intent(this, ComposeActivity.class);
        startActivityForResult(i, REQUEST_CODE_COMPOSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_COMPOSE && resultCode == RESULT_OK) {
            Log.i("DEBUG", "REQUEST_CODE: "+ requestCode);
            Log.i("DEBUG", "SUCCESS_CODE: " + resultCode);
            aTweets.clear();
            tweets.clear();
            populateTimeLine(0);
        }
    }
}
