package com.example.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Document(indexName = "zillow-item")
public class Item4ES {
    @Id
    private String id;
    @Field(type= FieldType.Keyword)
    private String rentType;
    @Field(type= FieldType.Keyword)
    private String price;
    @Field(type= FieldType.Text)
    private String aptType;
    @Field(type= FieldType.Keyword)
    private String imgs;
    @Field(type= FieldType.Text)
    private String title;
    @Field(type= FieldType.Keyword)
    private String city;

}
