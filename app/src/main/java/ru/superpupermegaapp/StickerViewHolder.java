package ru.superpupermegaapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by german on 12.06.16.
 */
public class StickerViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;

    public StickerViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.img);
    }

    public void bindItem(final Sticker sticker, final StickersAdapter.OnItemClickListener listener) {
        if (sticker.isFromAssets()) {
            try {
                InputStream inputStream = itemView.getContext().getResources().getAssets().open(sticker.getUri().toString());
                Bitmap bmp = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bmp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(sticker);
            }
        });
    }
}
