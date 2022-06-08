package com.example.myfitnesstracker.view.activities;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

import com.example.myfitnesstracker.R;

public class MySettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
    }

}
