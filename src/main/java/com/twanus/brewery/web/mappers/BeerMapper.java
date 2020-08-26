package com.twanus.brewery.web.mappers;

import com.twanus.brewery.domain.Beer;
import com.twanus.brewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToDto(Beer beer);

    Beer dtoToBeer(BeerDto beerDto);

}
