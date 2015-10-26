package com.codepath.apps.restclienttemplate.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rbammi on 10/24/15.
 */
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, android.R.layout.simple_list_item_1, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        Tweet tweet = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false );
        }

        ImageView profileImg  = (ImageView) convertView.findViewById(R.id.ivProfileImage);
        TextView username  = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView text  = (TextView) convertView.findViewById(R.id.tvBody);
        text.setText(tweet.getBody());
        username.setText(tweet.getUser().getScreenName());
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(profileImg);

        return convertView;
    }
}
