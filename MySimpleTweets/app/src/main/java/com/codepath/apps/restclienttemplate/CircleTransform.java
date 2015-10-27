package com.codepath.apps.restclienttemplate;

/**
 * Created by rbammi on 10/26/15.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

/**
 * Created by rbammi on 10/10/15.
 */

public class CircleTransform implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        // 1. Create Squaredbitmap
        // 2. Create Bitmap - > canvas -> paint
        // 3. Create Bitmapshader and assign Squaredbitmap
        // 4. set shreder to paint
        // 5. Draw circle with paint

        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();

        BitmapShader shader = new BitmapShader(squaredBitmap,
                BitmapShader.TileMode.MIRROR, BitmapShader.TileMode.MIRROR);
        paint.setShader(shader);
        //paint.setAntiAlias(true);


        // Create circle and put image.
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}
