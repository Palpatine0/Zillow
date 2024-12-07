package com.example.dao;

import com.example.entity.Preference;

public interface PreferenceDao {

    Preference findPreferenceByUserId(String userId);

    void savePreference(Preference preference);

    void updatePreferenceByUserId(String userId, String description);
}
