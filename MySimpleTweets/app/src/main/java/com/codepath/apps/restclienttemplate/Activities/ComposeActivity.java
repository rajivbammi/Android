package com.codepath.apps.restclienttemplate.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApplication;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

public class ComposeActivity extends AppCompatActivity {
    EditText etCompose;
    TextView tvCount;
    Button btCompose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etCompose = (EditText) findViewById(R.id.etCompose);
        tvCount = (TextView) findViewById(R.id.tvCount);
        btCompose = (Button) findViewById(R.id.btnCompose);

        etCompose.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int MAX_LIMIT = 140;
                tvCount.setText(Integer.toString(MAX_LIMIT - count - start));
                if (start + count > MAX_LIMIT ) {
                    etCompose.setTextColor(Color.RED);
                    tvCount.setTextColor(Color.RED);
                    btCompose.setEnabled(false);
                } else {
                    etCompose.setTextColor(Color.GRAY);
                    tvCount.setTextColor(Color.GRAY);
                    btCompose.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void postTweet(View view) {
        String tweetTxt  = etCompose.getText().toString();

        if (!tweetTxt.trim().matches("")) {
            Toast.makeText(this, "Posting tweet: " + tweetTxt, Toast.LENGTH_SHORT).show();
            TwitterClient client = TwitterApplication.getRestClient();
            client.composeTweet(tweetTxt, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
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
