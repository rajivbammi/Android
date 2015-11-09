package com.android.rbammi.hellowalmart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rbammi on 11/9/15.
 */
public class ProductArrayAdapter extends ArrayAdapter<Product> {

    public ProductArrayAdapter(Context context, ArrayList<Product> products) {
        super(context, R.layout.content_home, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        Product productItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }
        ImageView imgProduct = (ImageView) convertView.findViewById(R.id.ivProductImg);
        TextView tvProductTitle = (TextView) convertView.findViewById(R.id.tvProductName);

        tvProductTitle.setText(productItem.getItemName());
        Picasso.with(getContext()).load(productItem.getImgUrl())
                .into(imgProduct);

        return convertView;
    }
}
