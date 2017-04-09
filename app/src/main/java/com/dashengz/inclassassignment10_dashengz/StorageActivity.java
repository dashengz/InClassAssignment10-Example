package com.dashengz.inclassassignment10_dashengz;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

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
                            Picasso
                                    .with(StorageActivity.this)
                                    .load(localFile)
                                    .resize(imageView.getWidth(), imageView.getHeight())
                                    .centerInside()
                                    .into(imageView);
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
}
