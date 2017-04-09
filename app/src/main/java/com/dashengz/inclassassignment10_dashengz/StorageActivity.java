package com.dashengz.inclassassignment10_dashengz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.dashengz.inclassassignment10_dashengz.R.id.imageView;

public class StorageActivity extends AppCompatActivity {

    private StorageReference mStorageRef;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference uploadRef = mStorageRef.child("images/upload.jpg");
        imageView = (ImageView) findViewById(R.id.imageView);
        try {
            final File localFile = File.createTempFile("images", "jpg");
            uploadRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Successfully downloaded data to local file
                            try {
                                decodeUri(Uri.parse(localFile.toURI().toString()));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle failed download
                    // ...
                }
            });
        } catch (IOException ex) {
            // Error occurred while creating the File
        }
    }

    public void decodeUri(Uri uri) throws FileNotFoundException {

        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        // I just want to know the dimension, don't pass me the pixels yet!
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        Bitmap image = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, bmOptions);
        imageView.setImageBitmap(image);
    }
}
