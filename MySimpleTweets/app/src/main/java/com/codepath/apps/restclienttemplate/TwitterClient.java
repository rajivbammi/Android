package com.codepath.apps.restclienttemplate;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
	public static final String REST_URL = "https://api.twitter.com/1.1/"; // Change this, base API URL
	public static final String REST_CONSUMER_KEY = "QDNNOyr5u6u7HSa6CP99ZGk9k";       // Change this
	public static final String REST_CONSUMER_SECRET = "blOZWe3CyQlVRIRmuj1fnPeL5nLbKXEOB0kFKZIfO6ViAlxA5Z"; // Change this
	public static final String REST_CALLBACK_URL = "oauth://cpsimpletweet"; // Change this (here and in manifest)

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

    // Get timelines.
    public void getHomeTimelines(long max_id, AsyncHttpResponseHandler handler) {
        String url = getApiUrl("statuses/home_timeline.json");
        RequestParams params = new RequestParams();
        params.put("count", 15);
		params.put("since_id", 1);
		if(max_id != 0) {
			params.put("max_id", max_id);
		}
        getClient().get(url, params, handler);
    }

    // Composing a tweet
	public void composeTweet(String composeTxt, AsyncHttpResponseHandler handler) {
		String url = getApiUrl("statuses/update.json");
		RequestParams params = new RequestParams();
		params.put("status", composeTxt);
		getClient().post(url, params, handler);
    }
}