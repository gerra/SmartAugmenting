package ru.superpupermegaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by german on 11.06.16.
 */
public class TutorialFragment extends Fragment {
    public static final String TAG = TutorialFragment.class.getSimpleName();

    public static TutorialFragment newInstance() {
        return new TutorialFragment();
    }

    private View startStylingButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tutorial_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startStylingButton = view.findViewById(R.id.startStyling);
    }

    @Override
    public void onResume() {
        super.onResume();
        startStylingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((MainActivity) getActivity()).openFragment(CameraFragment.newInstance(), false);
                Intent intent = new Intent(getContext(), ARProgrammaticallyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        startStylingButton.setOnClickListener(null);
    }
}
