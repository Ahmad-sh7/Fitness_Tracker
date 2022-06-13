package com.example.myfitnesstracker.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.viewmodel.MainViewModel;


public class MoodEightFragment extends Fragment {

    private Button button;
    private SeekBar seekBar;
    private SeekBar seekBar2;
    private TextView textView;
    private TextView textView2;
    MainViewModel viewModel;





    public MoodEightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mood_eight, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seekBar = view.findViewById(R.id.seekBar8);
        textView = view.findViewById(R.id.textView20);
        seekBar2 = view.findViewById(R.id.seekBar9);
        textView2 = view.findViewById(R.id.textView18);
        viewModel =new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress2, boolean fromUser) {
                textView2.setText(String.valueOf(progress2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar2) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar2) {

            }
        });


        button = (Button) view.findViewById(R.id.zurück7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().finish();
            }
        });

    }
}