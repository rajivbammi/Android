package com.codepath.apps.restclienttemplate.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.Fragments.UserTimelineFragment;
import com.codepath.apps.restclienttemplate.Models.User;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApplication;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {
    private TwitterClient client;
    User user;

    public void setDefaultUserInfo() {
        client = TwitterApplication.getRestClient();
        client.getUserInfo(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //super.onSuccess(statusCode, headers, response);
                user = User.fromJson(response);
                getSupportActionBar().setTitle("@" + user.getScreenName());
                populateUserHeader(user);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //super.onFailure(statusCode, headers, responseString, throwable);
            }
        } );

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        String screen_name;
        User user = (User) getIntent().getSerializableExtra("user");

        if (user == null) {
            setDefaultUserInfo();
            screen_name = getIntent().getStringExtra("screen_name");
        } else {
            screen_name = user.getScreenName();
            populateUserHeader(user);
        }
        if (savedInstanceState == null) {
            Log.i("DEBUG", "savedInstanceState!= NULL");
            UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(screen_name);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContainer, userTimelineFragment);
            ft.commit();
        }
    }


    public void populateUserHeader(User user) {
        ImageView ivProfileImg = (ImageView) findViewById(R.id.ivUserProfileImg);
        TextView tvTagline = (TextView) findViewById(R.id.tvProfileTag);
        TextView tvProfileName = (TextView) findViewById(R.id.tvProfileName);
        TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        TextView tvFollower = (TextView) findViewById(R.id.tvFollowers);

        tvTagline.setText(user.getTagLine());
        tvProfileName.setText(user.getName());
        tvFollowing.setText(user.getFollowingCount() + " Following");
        tvFollower.setText(user.getFollowersCount() + " Followers");
        Picasso.with(this).load(user.getProfileImageUrl()).into(ivProfileImg);

    }
}
