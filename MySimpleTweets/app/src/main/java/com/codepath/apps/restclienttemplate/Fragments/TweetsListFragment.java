package com.codepath.apps.restclienttemplate.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.restclienttemplate.Adapter.TweetsArrayAdapter;
import com.codepath.apps.restclienttemplate.Listener.EndlessScrollListener;
import com.codepath.apps.restclienttemplate.Models.Tweet;
import com.codepath.apps.restclienttemplate.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbammi on 11/1/15.
 */
public class TweetsListFragment extends android.support.v4.app.Fragment {
    private TweetsArrayAdapter aTweets;
    private ArrayList<Tweet> tweets;
    private ListView lvTweets;

    // Creating custom listener
    public interface CustomScrollLoadListener {
        public void onCustomScrollLoad(Long maxId);
    }
    private CustomScrollLoadListener listener;
    public void setCustomScrollLoadListener(CustomScrollLoadListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("DEBUG", "inside onCreateView of Tweets list fragment");
        View v = inflater.inflate(R.layout.fragment_tweets_list, container, false);
        lvTweets = (ListView) v.findViewById(R.id.lvTimeline);
        lvTweets.setAdapter(aTweets);

        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                long max_id = Tweet.getMaxId() - 1;
                if(listener != null) {
                    listener.onCustomScrollLoad(max_id);
                    Log.i("DEBUG", "Firing event TweetsListFragment");
                }

                Log.i("DEBUG", "Calling onload more from onCreateView of TweetsListFragment");
                return true; // ONLY if more data is actually being loaded; false otherwise.
            }
        });
        return v;
    }

    public TweetsArrayAdapter getAdapter() {
        return aTweets;
    }

    public ListView getTweetListView() {
        return lvTweets;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("DEBUG", "inside oncreate of Tweets list fragment");

        this.listener = null;
        tweets = new ArrayList<>();
        aTweets = new TweetsArrayAdapter(getActivity(), tweets);
    }

    public void addAll(List<Tweet> tweets) {
        aTweets.addAll(tweets);
    }
}
