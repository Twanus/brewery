package com.twanus.brewery.web.mappers;

import com.twanus.brewery.domain.Customer;
import com.twanus.brewery.domain.Customer.CustomerBuilder;
import com.twanus.brewery.web.model.CustomerDto;
import com.twanus.brewery.web.model.CustomerDto.CustomerDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-25T23:53:27+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto customerToDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDtoBuilder customerDto = CustomerDto.builder();

        customerDto.id( customer.getId() );
        customerDto.name( customer.getName() );

        return customerDto.build();
    }

    @Override
    public Customer dtoToCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        CustomerBuilder customer = Customer.builder();

        customer.id( customerDto.getId() );
        customer.name( customerDto.getName() );

        return customer.build();
    }
}
