package com.example.rbammi.gridimagesearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.rbammi.gridimagesearch.R;

public class SettingsActivity extends AppCompatActivity {

    Spinner spImageSize;
    Spinner spColorFilter;
    Spinner spImageType;
    EditText etSiteFilter;
    int RESULTS_OK = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        spImageSize = (Spinner) findViewById(R.id.spinnerImgSize);
        spColorFilter =  (Spinner) findViewById(R.id.spinnerColorFilter);
        spImageType =  (Spinner) findViewById(R.id.spinnerImageType);
        etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);
    }

    public void onSettingSave(View view) {
        String imageSize = spImageSize.getSelectedItem().toString();
        String colorFilter = spColorFilter.getSelectedItem().toString();
        String imageType = spImageType.getSelectedItem().toString();
        String siteFilter = etSiteFilter.getText().toString();
        Intent data = new Intent();
        data.putExtra("imageSize", imageSize);
        data.putExtra("colorFilter", colorFilter);
        data.putExtra("imageType", imageType);
        data.putExtra("siteFilter", siteFilter);
        setResult(RESULTS_OK, data);
        finish();
    }
}
