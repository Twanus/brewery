package com.twanus.brewery.web.mappers;

import com.twanus.brewery.domain.Beer;
import com.twanus.brewery.domain.Beer.BeerBuilder;
import com.twanus.brewery.web.model.BeerDto;
import com.twanus.brewery.web.model.BeerDto.BeerDtoBuilder;
import com.twanus.brewery.web.model.v2.BeerStyleEnum;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-25T23:53:27+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
@Component
public class BeerMapperImpl implements BeerMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public BeerDto beerToDto(Beer beer) {
        if ( beer == null ) {
            return null;
        }

        BeerDtoBuilder beerDto = BeerDto.builder();

        beerDto.id( beer.getId() );
        beerDto.beerName( beer.getBeerName() );
        if ( beer.getBeerStyle() != null ) {
            beerDto.beerStyle( beer.getBeerStyle().name() );
        }
        beerDto.upc( beer.getUpc() );
        beerDto.createdDate( dateMapper.asOffsetDateTime( beer.getCreatedDate() ) );

        return beerDto.build();
    }

    @Override
    public Beer dtoToBeer(BeerDto beerDto) {
        if ( beerDto == null ) {
            return null;
        }

        BeerBuilder beer = Beer.builder();

        beer.id( beerDto.getId() );
        beer.beerName( beerDto.getBeerName() );
        if ( beerDto.getBeerStyle() != null ) {
            beer.beerStyle( Enum.valueOf( BeerStyleEnum.class, beerDto.getBeerStyle() ) );
        }
        beer.upc( beerDto.getUpc() );
        beer.createdDate( dateMapper.asTimestamp( beerDto.getCreatedDate() ) );

        return beer.build();
    }
}
