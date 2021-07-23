package com.dicoding.courseschedule.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.notification.DailyReminder

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        //TODO 10 : Update theme based on value in ListPreference
        //TODO 11 : Schedule and cancel notification in DailyReminder based on SwitchPreference
        val theme = findPreference<ListPreference>(getString(R.string.pref_key_dark))
        val notification = findPreference<SwitchPreference>(getString(R.string.pref_key_notify))
        val dailyReminder = DailyReminder()

        theme?.setOnPreferenceChangeListener { _, newValue ->
            when (newValue) {
                getString(R.string.pref_dark_auto) -> {
                    updateTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
                getString(R.string.pref_dark_on) -> {
                    updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
                }
                else -> {
                    updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
            return@setOnPreferenceChangeListener true
        }

        notification?.setOnPreferenceChangeListener { _, newValue ->
            if (newValue == true){
                context?.let { dailyReminder.setDailyReminder(it) }
            } else{
                context?.let { dailyReminder.cancelAlarm(it) }
            }
            return@setOnPreferenceChangeListener true
        }
    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }
}