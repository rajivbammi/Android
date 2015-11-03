package com.codepath.apps.restclienttemplate.Adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.Models.Tweet;
import com.codepath.apps.restclienttemplate.Models.User;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.Utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by rbammi on 10/24/15.
 */
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

    // Creating custom listener
    public interface ProfileImageListener {
        public void onProfileImgSelected(User user);
    }

    private ProfileImageListener listener;

    public void setProfileImageListener(ProfileImageListener listener) {
        this.listener = listener;
    }

    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, android.R.layout.simple_list_item_1, tweets);
        this.listener = null;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        final Tweet tweet = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false );
        }

        ImageView profileImg  = (ImageView) convertView.findViewById(R.id.ivProfileImage);
        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onProfileImgSelected(tweet.getUser());
                }
            }
        });

        TextView tvUsername  = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvText  = (TextView) convertView.findViewById(R.id.tvBody);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        String timeStr = tweet.getCreatedAt();
        timeStr = getRelativeTimeAgo(timeStr);

        tvTime.setText(timeStr);
        tvText.setText(tweet.getBody());
        tvUsername.setText(tweet.getUser().getScreenName());
        profileImg.setImageURI(null);
        Picasso.with(getContext())
                .load(tweet.getUser().getProfileImageUrl())
                .transform(new CircleTransform())
                .into(profileImg);

        return convertView;
    }

    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}
