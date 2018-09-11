package com.example.android.quakereport;

import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public static class EarthquakePreferences extends PreferenceFragment implements Preference.OnPreferenceChangeListener{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_layout);

            Preference minmagnitude=findPreference(getString(R.string.settings_min_magnitude_key));
            bindPreferenceSummarytoValue(minmagnitude);

            Preference orderby=findPreference(getString(R.string.settings_orderby_key));
            bindPreferenceSummarytoValue(orderby);
        }

        public void bindPreferenceSummarytoValue(Preference minmagnitude) {
            minmagnitude.setOnPreferenceChangeListener(this);
            SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(minmagnitude.getContext());
            String value=preferences.getString(minmagnitude.getKey(), "");
            onPreferenceChange(minmagnitude,value );
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if (prefIndex >= 0) {
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[prefIndex]);
                }
            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }
        }
    }

