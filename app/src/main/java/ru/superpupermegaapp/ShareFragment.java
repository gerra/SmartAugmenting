package ru.superpupermegaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by german on 12.06.16.
 */
public class ShareFragment extends Fragment {

    public static ShareFragment newInstance() {
        return new ShareFragment();
    }

    private static String IS_EXTERNAL_STORAGE_AVAILABLE = "is_external_storage_available";
    private View buttonForSharing;
    private View sharingImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.share_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonForSharing = view.findViewById(R.id.buttonShare);
        sharingImage = view.findViewById(R.id.img);
    }

    @Override
    public void onResume() {
        super.onResume();
        buttonForSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertViewToPNG(sharingImage);
                String title = getString(R.string.shareMessageTitle);
                startActivity(Intent.createChooser(getSharingIntent(getTmpImgFile(), ""), title));
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        buttonForSharing.setOnClickListener(null);
    }

    public void convertViewToPNG(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap b = view.getDrawingCache();
        try {
            File imgFile = getTmpImgFile();
            if (!imgFile.exists()) {
                if (!imgFile.createNewFile()) {
                    return;
                }
            }
            FileOutputStream stream = new FileOutputStream(imgFile);
            try {
                b.compress(Bitmap.CompressFormat.PNG, 80, stream);
            } finally {
                stream.close();
                view.setDrawingCacheEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static Intent getSharingIntent(File file, String text) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        if (file != null) {
            sharingIntent.setType("image/png");
            sharingIntent.putExtra(android.content.Intent.EXTRA_STREAM, Uri.fromFile(file));
        } else {
            sharingIntent.setType("text/plain");
        }
        return sharingIntent;
    }

    private File getTmpImgFile() {
        return new File(getDataFolder("img"), "tmp.png");
    }

    private File getDataFolder(String type) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        if (!preferences.getBoolean(IS_EXTERNAL_STORAGE_AVAILABLE, false)) {
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) { //using external storage
                preferences.edit().putBoolean(IS_EXTERNAL_STORAGE_AVAILABLE, true).apply();
            }
        }
        if (preferences.getBoolean(IS_EXTERNAL_STORAGE_AVAILABLE, false)) {
            return this.getContext().getExternalFilesDir(type);
        } else {
            return this.getContext().getFilesDir();
        }
    }
}
