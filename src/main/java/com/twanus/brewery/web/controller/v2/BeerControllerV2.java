package com.twanus.brewery.web.controller.v2;

import com.twanus.brewery.services.v2.BeerServiceV2;
import com.twanus.brewery.web.model.v2.BeerDtoV2;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
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

@Slf4j
@Validated
@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

    private final BeerServiceV2 beerServiceV2;

    public BeerControllerV2(BeerServiceV2 beerServiceV2) {
        this.beerServiceV2 = beerServiceV2;
    }

    @GetMapping({"/{beerId}"}) // GET BEER BY ID
    public ResponseEntity<BeerDtoV2> getBeer(@NotNull @PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
    }
    @PostMapping // CREATE NEW BEER
    public ResponseEntity<?> handlePost(@Valid @RequestBody BeerDtoV2 beerDto) {

        log.debug("in handlePost()...");

        val b = beerServiceV2.saveNewBeer(beerDto);
        val headers = new HttpHeaders();
        String url = "http://localhost:8080/api/v2/beer/" + b.getId();
        headers.add("location", url);

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    @PutMapping({"/{beerId}"}) // UPDATE BEER
    public ResponseEntity<?> handeUpdate(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDtoV2 beerDto) {
        beerServiceV2.updateBeer(beerId, beerDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"}) // DELETE BEER
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId){
        beerServiceV2.deleteById(beerId);
    }

}
