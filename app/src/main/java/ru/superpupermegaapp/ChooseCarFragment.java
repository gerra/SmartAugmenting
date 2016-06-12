package ru.superpupermegaapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by german on 11.06.16.
 */
public class ChooseCarFragment extends Fragment {
    public static final String TAG = ChooseCarFragment.class.getSimpleName();

    private static final String[] CARS = new String[] {
        "Марка", "Smart", "Ford", "BMW", "Mercedes", "Hyundai", "Honda", "Mazda"
    };
    private static final String[] MODELS = new String[] {
            "Модель", "Crossblade", "K", "Forfour", "Fortwo", "Roadster", "City-Coupe"
    };

    public static ChooseCarFragment newInstance() {
        return new ChooseCarFragment();
    }

    private View submitButton;
    private AppCompatSpinner carSpinner;
    private ChooseSpinnerItemAdapter carSpinnerAdapter;
    private AppCompatSpinner modelSpinner;
    private ChooseSpinnerItemAdapter modelSpinnerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carSpinnerAdapter = new ChooseSpinnerItemAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, CARS);
        modelSpinnerAdapter = new ChooseSpinnerItemAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, MODELS);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_car_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        submitButton = view.findViewById(R.id.submit);
        carSpinner = (AppCompatSpinner) view.findViewById(R.id.chooseCarSpinner);
        carSpinner.setAdapter(carSpinnerAdapter);
        modelSpinner = (AppCompatSpinner) view.findViewById(R.id.chooseModelSpinner);
        modelSpinner.setAdapter(modelSpinnerAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openFragment(TutorialFragment.newInstance(), false);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        submitButton.setOnClickListener(null);
    }

    private static class ChooseSpinnerItemAdapter extends ArrayAdapter<String> {

        public ChooseSpinnerItemAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            setDropDownViewResource(resource);
        }

        @Override
        public boolean isEnabled(int position) {
            return position != 0;
        }

        private View getView(int position, View view) {
            TextView tv = (TextView) view;
            if (position == 0) {
                // Set the hint text color gray
                tv.setTextColor(Color.GRAY);
            } else {
                tv.setTextColor(Color.BLACK);
            }
            return view;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getView(position, super.getView(position, convertView, parent));
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view = super.getDropDownView(position, convertView, parent);
            return getView(position, view);
        }
    }
}
