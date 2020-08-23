package com.twanus.brewery;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twanus.brewery.services.BeerService;
import com.twanus.brewery.web.controller.BeerController;
import com.twanus.brewery.web.model.BeerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @MockBean BeerService beerService;
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    BeerDto validBeer;

    @Before
    public void setUp() {
        validBeer = BeerDto.builder()
                .beerStyle("PALE ALE")
                .beerName("Hoegaarden")
                .upc(123456798012L)
                .build();
    }

    @Test
    public void getBeer() throws Exception {
        given(beerService.getBeerById(any(UUID.class)))
                .willReturn(validBeer);

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testSaveNewBeer() throws Exception {
        BeerDto beerDto = validBeer;

        beerDto.setId(UUID.randomUUID());
        BeerDto savedBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("new beer")
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any()))
                .willReturn(savedBeer);

        mockMvc.perform(
                    post("/api/v1/beer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))

                .andExpect(status().isCreated());

    }
    @Test
    public void handleUpdate() throws Exception {
        BeerDto beerDto = validBeer;
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        //when
        mockMvc.perform(
                put("/api/v1/beer/" + UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(beerDtoJson))

                .andExpect(status().isNoContent());

        then(beerService).should().updateBeer(any(), any());

    }
}
