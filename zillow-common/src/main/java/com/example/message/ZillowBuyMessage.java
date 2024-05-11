package com.example.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ZillowBuyMessage implements Serializable {
    private String userId;
    private String itemId;
    private String price;
    private String startDate;
    private String endDate;
}
