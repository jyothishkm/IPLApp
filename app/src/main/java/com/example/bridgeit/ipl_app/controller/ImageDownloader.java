package com.example.bridgeit.ipl_app.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.example.bridgeit.ipl_app.myinetrface.ImageDownloaderInterface;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by bridgeit on 24/11/16.
 */

public class ImageDownloader {
    public static Bitmap mBitmap;

    public static void downloadImage(String url, final ImageDownloaderInterface image){
       FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference().child(url);

        final long IMAGE_SIZE = 1024*1024;
        reference.getBytes(IMAGE_SIZE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
           mBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                image.getImage(mBitmap);
            }
        });

    }
}
