package com.codepath.apps.restclienttemplate.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.restclienttemplate.Adapter.TweetsArrayAdapter;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_tweets_list, container, false);
        lvTweets = (ListView) v.findViewById(R.id.lvTimeline);
        lvTweets.setAdapter(aTweets);
        return v;
    }

    public TweetsArrayAdapter getAdapter() {
        return aTweets;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweets = new ArrayList<>();
        aTweets = new TweetsArrayAdapter(getActivity(), tweets);
    }

    public void addAll(List<Tweet> tweets) {
        aTweets.addAll(tweets);
    }
}
