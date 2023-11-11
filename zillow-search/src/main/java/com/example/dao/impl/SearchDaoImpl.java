package com.example.dao.impl;


import com.example.dao.SearchDao;
import com.example.entity.Item;
import com.example.entity.Item4ES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Value("${zillow.search.init.enabled}")
    private boolean initEnabled = false;

    @Override
    public void batchInsertToES(List<Item4ES> items) {
        // creating an Elasticsearch index if it doesn't already exist.
        if (initEnabled) {
            createIndex();
        }

        // convert to Index, so it can be inserted to ES
        ArrayList<IndexQuery> list = new ArrayList<>();
        for (Item4ES item : items) {
            list.add(new IndexQueryBuilder().withObject(item).build());
        }
        // insert
        elasticsearchRestTemplate.bulkIndex(list, Item4ES.class);
    }

    private void createIndex() {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(Item4ES.class);
    }


    @Override
    public List<Item4ES> pageQuery(String city, String content, int page, int rows) {

        return null;
    }
}
