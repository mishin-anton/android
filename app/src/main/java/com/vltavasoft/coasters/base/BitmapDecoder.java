package com.vltavasoft.coasters.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.vltavasoft.coasters.R;

import java.io.File;

public class BitmapDecoder {

    Context context;

    BitmapDecoder (Context context) {
        this.context = context;
        Log.d("CONT", "Value Bitmap decode" + context);
    }

    public Bitmap readAndSetImage() {
        int px = context.getResources().getDimensionPixelSize(R.dimen.image_size);
        // Below URI for test
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "BC127__86042_sm.jpg");
        Bitmap bitmap = decodeSampleBitmapFromResource(file.getAbsolutePath(), px, px);
        Log.d("DECODE", String.format("Required size = %s, bitmap size = %sx%s, byteCount = %s",
                px, bitmap.getWidth(), bitmap.getHeight(), bitmap.getByteCount()));
        return bitmap;
    }

    private Bitmap decodeSampleBitmapFromResource(String path, int reqWidth, int reqHeight) {

        // Читаем с inJustDecodeBounds=true для определения размеров
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Вычисляем inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Читаем с использованием inSampleSize коэффициента
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        // Реальные размеры изображения
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqHeight) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Вычисляем наибольший inSampleSize, который будет кратным двум
            // и оставит полученные размеры больше, чем требуемые
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth){
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
