package com.twanus.brewery.services;

import com.twanus.brewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Antoon")
                .build();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto c) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("nieve kalant")
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        //TODO-DLS
    }

    @Override
    public void deleteById(UUID customerId) {
        //TODO-DLS
    }

}
