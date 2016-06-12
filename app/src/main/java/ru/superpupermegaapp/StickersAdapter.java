package ru.superpupermegaapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by german on 12.06.16.
 */
public class StickersAdapter extends RecyclerView.Adapter<StickerViewHolder> {

    public interface OnItemClickListener {
        void onClick(Sticker sticker);
    }

    private List<Sticker> stickers;
    private OnItemClickListener listener;

    public StickersAdapter(OnItemClickListener listener) {
        stickers = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public StickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View stickerView = inflater.inflate(R.layout.sticker_item, parent, false);
        return new StickerViewHolder(stickerView);
    }

    @Override
    public void onBindViewHolder(StickerViewHolder holder, int position) {
        holder.bindItem(stickers.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return stickers.size();
    }

    public void addSticker(Sticker sticker) {
        stickers.add(sticker);
        notifyItemInserted(stickers.size() - 1);
    }
}
