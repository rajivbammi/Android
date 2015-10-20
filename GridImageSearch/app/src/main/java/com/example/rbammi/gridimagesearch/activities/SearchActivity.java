package com.example.rbammi.gridimagesearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.rbammi.gridimagesearch.EndlessScrollListener;
import com.example.rbammi.gridimagesearch.R;
import com.example.rbammi.gridimagesearch.adapters.ImageResultArrayAdapter;
import com.example.rbammi.gridimagesearch.models.ImageResult;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity {

    Button btnSearch;
    EditText etQuery;
    GridView gvResults;
    String SEARCH_URL = "https://ajax.googleapis.com/ajax/services/search/images?rsz=8&v=1.0&q=";
    ArrayList <ImageResult> imgResults;
    ImageResultArrayAdapter imageAdapter;
    String gFilterImageSize = "";
    String gFilterColorFilter = "";
    String gFilterImageType  = "";
    String gFilterSite  = "";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        System.out.println("hete");
        switch(item.getItemId()) {
            case R.id.miSettings:
                showSettingsScreen();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showSettingsScreen() {
        Toast.makeText(this, "Opening Settings", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(SearchActivity.this, SettingsActivity.class);
        startActivityForResult(i, 200);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        //System.out.println("inside onActivityResult");
        //System.out.println(data.getStringExtra("imageSize"));
        gFilterImageSize = data.getStringExtra("imageSize");
        gFilterColorFilter = data.getStringExtra("colorFilter");
        gFilterImageType = data.getStringExtra("imageType");
        gFilterSite = data.getStringExtra("siteFilter");
    }

    public void setViews() {
        btnSearch = (Button) findViewById(R.id.btnSearch);
        etQuery = (EditText) findViewById(R.id.etQuery);
        gvResults = (GridView) findViewById(R.id.gvResults);
        gvResults.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                Log.i("Debug", "page number:" + page + "totalItemsCount" + totalItemsCount);
                int offset = page * 8;
                fetchData(offset, etQuery.getText().toString());
                return true;
            }
        });

        imgResults = new ArrayList<ImageResult>();
        imageAdapter = new ImageResultArrayAdapter(this, R.id.gvResults ,imgResults);
        gvResults.setAdapter(imageAdapter);
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
                ImageResult imgResult = imgResults.get(position);
                i.putExtra("img_res", imgResult);
                startActivity(i);
            }
        });
    }

    public void fetchData(int pageNum, String query) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = SEARCH_URL + query + getSearchQueryURL() + "&start=" + pageNum;
        Log.d("Debug", "Query Url is " + url);
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                JSONArray imgJsonResults;
                try {
                    imgJsonResults = response.getJSONObject("responseData").getJSONArray("results");
                    //imageAdapter.clear();
                    imageAdapter.addAll(ImageResult.fromJsonArray(imgJsonResults));
                    Log.d("Debug", imgResults.toString());
                } catch (JSONException e) {
                    Log.d("Debug", "Error in parsing feed" + e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    public String getSearchQueryURL() {
        String query = "&imgsz=" + gFilterImageSize +
                       "&imgcolor=" + gFilterColorFilter +
                       "&imgtype=" + gFilterImageType +
                       "&as_sitesearch=" + gFilterSite;
        return query;
    }
    public void onImageSearch(View view) {
        String query = etQuery.getText().toString();
        Toast.makeText(this, "Finding images for " + query, Toast.LENGTH_SHORT).show();
        imageAdapter.clear();
        fetchData(1, query);
        //Intent i = new Intent(R.class.ImageDisplayActivity);
    }
}
