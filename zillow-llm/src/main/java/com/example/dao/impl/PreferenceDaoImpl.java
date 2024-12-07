package com.example.dao.impl;

import com.example.dao.PreferenceDao;
import com.example.entity.Item;
import com.example.entity.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PreferenceDaoImpl implements PreferenceDao {
    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Preference findPreferenceByUserId(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, Preference.class);
    }

    @Override
    public void savePreference(Preference preference) {
        mongoTemplate.save(preference);
    }

    @Override
    public void updatePreferenceByUserId(String userId, String description) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        Update update = new Update();
        update.set("description", description);
        mongoTemplate.findAndModify(query, update, Preference.class);
    }
}
