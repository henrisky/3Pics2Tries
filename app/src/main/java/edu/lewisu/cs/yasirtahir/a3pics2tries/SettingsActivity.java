package edu.lewisu.cs.yasirtahir.a3pics2tries;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import androidx.preference.DialogPreference.TargetFragment;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    //.replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean darkTheme = sharedPreferences.getBoolean(getString(R.string.pref_theme_key), false);
        if(darkTheme){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }

//    public static class SettingsFragment extends PreferenceFragmentCompat {
//        @Override
//        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
//
//            SwitchPreference themePref = findPreference(getString(R.string.pref_theme_key));
//            if(themePref != null){
//                themePref.setOnPreferenceChangeListener((preference, newValue) -> {
//                    requireActivity().recreate();
//                    return true;
//                });
//            }
//        }
//    }
}
