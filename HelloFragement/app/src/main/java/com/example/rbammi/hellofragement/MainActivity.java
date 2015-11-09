package com.example.rbammi.hellofragement;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_fragment);

        if(savedInstanceState == null) {
            Log.i("Debug", "inside");
            FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.flFragement, new DynamicFragment(), "dynamicfragment");
            fm.commit();
        }
    }
}
