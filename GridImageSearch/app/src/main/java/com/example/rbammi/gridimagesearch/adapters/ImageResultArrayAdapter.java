package com.example.rbammi.gridimagesearch.adapters;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rbammi.gridimagesearch.R;
import com.example.rbammi.gridimagesearch.models.ImageResult;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rbammi on 10/15/15.
 */
public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {

    public ImageResultArrayAdapter(Context context, int resource, List<ImageResult> images) {
        //super(context, android.R.layout.simple_list_item_1, images);
        super(context, R.layout.item_image_result, images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("Debug", "Inside for row" + position);
        // Get the item for the position.
        ImageResult imgRes = getItem(position);
        // Check existing view being used?
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_image_result, parent, false);
        }
        // find the imageview with in created view or reused view.
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivSearchImg);
        imageView.setImageResource(0);
        Picasso.with(getContext())
                .load(imgRes.getThumbUrl())
                .into(imageView);

        /*imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("inside click");
            }
        });*/

        TextView textView = (TextView) convertView.findViewById(R.id.tvTitle);
        textView.setText(Html.fromHtml(imgRes.getTitle()));

        return convertView;
    }
}
