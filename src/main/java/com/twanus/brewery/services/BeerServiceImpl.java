package com.twanus.brewery.services;

import com.twanus.brewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Stella")
                .beerStyle("pils")
                .upc(12345678901L)
                .build();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Jupiler")
                .beerStyle("pils")
                .upc(12345678902L)
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //TODO code this =)
    }

    @Override
    public void deleteById(UUID beerId) {
        log.debug("Deleting a beer...");

    }


}
