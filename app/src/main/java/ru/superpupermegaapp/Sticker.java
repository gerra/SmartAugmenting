package ru.superpupermegaapp;

import android.net.Uri;

/**
 * Created by german on 12.06.16.
 */
public class Sticker {
    private Uri uri;
    private boolean isFromAssets;

    public Sticker(Uri uri, boolean isFromAssets) {
        this.uri = uri;
        this.isFromAssets = isFromAssets;
    }

    public Uri getUri() {
        return uri;
    }

    public boolean isFromAssets() {
        return isFromAssets;
    }
}
