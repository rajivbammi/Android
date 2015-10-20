package com.example.rbammi.gridimagesearch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.rbammi.gridimagesearch.R;
import com.example.rbammi.gridimagesearch.models.ImageResult;
import com.squareup.picasso.Picasso;

public class ImageDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        ImageView imageView = (ImageView) findViewById(R.id.ivFullImage);
        ImageResult imageResult =  (ImageResult) getIntent().getSerializableExtra("img_res");
        Picasso.with(this).load(imageResult.getImgUrl())
                .into(imageView);
    }
}
