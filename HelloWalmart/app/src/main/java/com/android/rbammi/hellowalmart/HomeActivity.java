package com.android.rbammi.hellowalmart;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class HomeActivity extends AppCompatActivity {
    EditText etQuery;
    String API_KEY = "";
    String PRODUCT_SEARCH_URL = "http://api.walmartlabs.com/v1/search?";
    ArrayList<Product> productList;
    //ArrayAdapter<Product> aProducts;
    ProductArrayAdapter aProducts;
    ListView lvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
        etQuery = (EditText) findViewById(R.id.etSearchQuery);
        productList = new ArrayList<Product>();
        //aProducts =   new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, productList);
        aProducts = new ProductArrayAdapter(this, productList);

        lvProducts = (ListView) findViewById(R.id.lvProducts);
        lvProducts.setAdapter(aProducts);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSearchSubmit(View view) {
        String query = etQuery.getText().toString();
        String searchUrl = PRODUCT_SEARCH_URL + "apiKey=" + API_KEY +
                "&query=" + query;
        Toast.makeText(this, "Searching products for : " + query, Toast.LENGTH_SHORT).show();
        Log.i("DEBUG", "URL is: " + searchUrl);
        fetchSearchResult(searchUrl);
    }

    public void fetchSearchResult(String searchUrl) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(searchUrl, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //super.onSuccess(statusCode, headers, response);
                Log.i("DEBUG", "Success: " + response.toString());
                try {
                    ArrayList <Product> productList =
                            Product.fromJsonArray(response.getJSONArray("items"));
                    aProducts.clear();
                    aProducts.addAll(productList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Add it to adapter
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //super.onFailure(statusCode, headers, responseString, throwable);
                Log.i("DEBUG", "Failure: " + responseString.toString());
            }
        });
    }
}
