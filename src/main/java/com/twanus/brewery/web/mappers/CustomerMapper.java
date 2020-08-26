package com.twanus.brewery.web.mappers;

import com.twanus.brewery.domain.Customer;
import com.twanus.brewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto customerToDto(Customer customer);

    Customer dtoToCustomer(CustomerDto customerDto);
}
