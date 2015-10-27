package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

public class ComposeActivity extends AppCompatActivity {

    EditText etCompose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etCompose = (EditText) findViewById(R.id.etCompose);
    }

    public void postTweet(View view) {
        String tweetTxt  = etCompose.getText().toString();

        if (!tweetTxt.trim().matches("")) {
            Toast.makeText(this, "Posting tweet: " + tweetTxt, Toast.LENGTH_SHORT).show();
            TwitterClient client = TwitterApplication.getRestClient();
            client.composeTweet(tweetTxt, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    //Toast.makeText(getApplicationContext(), "Message posted successfully",
                            //Toast.LENGTH_SHORT).show();
                    Log.i("DEBUG", "Message posted successfully");
                    Intent data = new Intent();
                    setResult(RESULT_OK, data);
                    finish();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.i("Debug", "Error in composing tweet: " + error.getMessage());
                }
            });
        }
    }
}
