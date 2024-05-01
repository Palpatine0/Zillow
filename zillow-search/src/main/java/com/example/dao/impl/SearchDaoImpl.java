package com.example.dao.impl;


import com.example.dao.SearchDao;
import com.example.entity.Item4ES;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchDaoImpl implements SearchDao {


    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Value("${zillow.search.init.enabled}")
    private boolean initEnabled = false;


    @Override
    public List<Item4ES> searchByCity(String city, int page, int rows) {

        // Create search condition object
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("city", city));


        // Create search condition object
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(PageRequest.of(page, rows))
                .build();

        long totalCount = elasticsearchRestTemplate.count(query, Item4ES.class);

        // Perform search
        SearchHits<Item4ES> resultPage = elasticsearchRestTemplate.search(query, Item4ES.class);
        List<SearchHit<Item4ES>> searchHits = resultPage.getSearchHits();
        List<Item4ES> res = new ArrayList<>();

        // Process search hits
        for (SearchHit<Item4ES> searchHit : searchHits) {
            Item4ES item4ES = new Item4ES();
            item4ES.setId(searchHit.getContent().getId());
            item4ES.setRentType(searchHit.getContent().getRentType());
            item4ES.setPrice(searchHit.getContent().getPrice());
            item4ES.setImgs(searchHit.getContent().getImgs());
            item4ES.setAptType(searchHit.getContent().getAptType());
            item4ES.setCity(searchHit.getContent().getCity());
            item4ES.setTitle(searchHit.getContent().getTitle());
            item4ES.setTotalCount(totalCount);
            String link = item4ES.getLink();
            item4ES.setLink(link);
            res.add(item4ES);
        }

        return res;
    }

    @Override
    public List<Item4ES> searchByKeyWord(String city, String content, int page, int rows) {

        // S1: create search condition object
        // query condition
        BoolQueryBuilder shouldBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title", content))
                .should(QueryBuilders.matchQuery("houseType", content))
                .should(QueryBuilders.matchQuery("rentType", content));

        // relation with condition. everything must base on current city
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(QueryBuilders.matchQuery("city", city)).must(shouldBuilder);

        // create search condition object
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(PageRequest.of(page, rows))
                .build();

        // S2: search
        SearchHits<Item4ES> resultPage = elasticsearchRestTemplate.search(query, Item4ES.class);
        List<SearchHit<Item4ES>> searchHits = resultPage.getSearchHits();
        ArrayList<Item4ES> res = new ArrayList<>();
        for (SearchHit<Item4ES> searchHit : searchHits) {
            Item4ES item4ES = new Item4ES();
            item4ES.setId(searchHit.getContent().getId());
            item4ES.setRentType(searchHit.getContent().getRentType());
            item4ES.setPrice(searchHit.getContent().getPrice());
            item4ES.setImgs(searchHit.getContent().getImgs());
            item4ES.setAptType(searchHit.getContent().getAptType());
            item4ES.setCity(searchHit.getContent().getCity());
            item4ES.setTitle(searchHit.getContent().getTitle());
            res.add(item4ES);
        }

        return res;
    }


    // use test function in springboot to call this method and create index in ES
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


}
