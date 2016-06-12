package ru.superpupermegaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by german on 12.06.16.
 */
public class CameraActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_activity);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, CameraFragment.newInstance())
                .commit();
    }
}
