package ru.superpupermegaapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

/**
 * Created by german on 12.06.16.
 */
public class CameraFragment extends Fragment {

    public static Fragment newInstance() {
        return new CameraFragment();
    }

    private RecyclerView stickersList;
    private StickersAdapter stickersAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        stickersAdapter = new StickersAdapter();
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.camera_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stickersList = (RecyclerView) view.findViewById(R.id.stickersList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        stickersList.setLayoutManager(linearLayoutManager);
        stickersList.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.HORIZONTAL_LIST));
        stickersList.setAdapter(stickersAdapter);

        try {
            String[] paths = getContext().getResources().getAssets().list("");
            for (String path : paths) {
                if (!path.endsWith(".png")) {
                    continue;
                }
                Uri stickerUri = Uri.parse(path);
                Sticker sticker = new Sticker(stickerUri, true);
                stickersAdapter.addSticker(sticker);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
