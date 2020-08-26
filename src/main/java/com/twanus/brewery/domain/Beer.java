package com.twanus.brewery.domain;


import com.twanus.brewery.web.model.v2.BeerStyleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {

    private UUID id;
    private String beerName;
    private BeerStyleEnum beerStyle;
    private long upc;

    private Timestamp createdDate;
    private Timestamp lastUpdateDate;
}
