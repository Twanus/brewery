package com.twanus.brewery.web.controller;

import com.twanus.brewery.services.BeerService;
import com.twanus.brewery.web.model.BeerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Deprecated
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }
    @PostMapping // CREATE NEW BEER
    public ResponseEntity<?> handlePost(BeerDto beerDto) {
        BeerDto b = beerService.saveNewBeer(beerDto);
        HttpHeaders headers = new HttpHeaders();
        String url = "http://localhost:8080/api/v1/beer/";
        headers.add("location", url + b.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    @PutMapping({"/{beerId}"}) // UPDATE BEER
    public ResponseEntity<?> handeUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
        beerService.updateBeer(beerId, beerDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId){
        beerService.deleteById(beerId);
    }

}
