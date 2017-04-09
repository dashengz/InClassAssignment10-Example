package com.dashengz.inclassassignment10_dashengz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toActivity(View view) {

        switch (((Button) view).getText().toString()) {
            case "Object":
                startActivity(new Intent(this, ObjectActivity.class));
                break;
            case "List":
                startActivity(new Intent(this, ListActivity.class));
                break;
            case "Camera":
                startActivity(new Intent(this, CameraActivity.class));
                break;
            case "Storage":
                startActivity(new Intent(this, StorageActivity.class));
                break;
        }

    }
}
