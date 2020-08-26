package com.twanus.brewery.web.controller;

import com.twanus.brewery.services.CustomerService;
import com.twanus.brewery.web.model.CustomerDto;
import com.twanus.brewery.web.model.v2.BeerDtoV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RequestMapping("api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@NotNull @PathVariable("customerId") UUID customerId) {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> handlePost(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto c = customerService.saveNewCustomer(customerDto);
        HttpHeaders headers = new HttpHeaders();
        String url = "http://localhost:8080/api/v1/customer/" + c.getId();
        headers.add("location", url);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{customerId}"})
    public ResponseEntity<?> handeUpdate(@PathVariable("customerId") UUID customerId, @Valid @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(customerId, customerDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{customerId}"}) // DELETE BEER
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@NotNull @PathVariable("customerId") UUID customerId){
        customerService.deleteById(customerId);
    }

}
