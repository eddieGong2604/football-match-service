package com.example.footballleague.controller;

import com.example.footballleague.dto.request.create.CountryCreateRequestDTO;
import com.example.footballleague.dto.request.filter.CountryFilterRequestDTO;
import com.example.footballleague.model.Country;
import com.example.footballleague.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryController.class)
public class CountryControllerTests {
    @MockBean
    CountryService countryService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getCountries() throws Exception {
        String countryName = "England";
        UUID countryId = UUID.randomUUID();
        CountryFilterRequestDTO countryFilterRequestDTO = CountryFilterRequestDTO.builder()
                .countryName(countryName)
                .build();
        when(countryService.getCountries(countryFilterRequestDTO)).thenReturn(
                Collections.singletonList(Country.builder()
                        .id(countryId).countryName(countryName)
                        .build())
        );
        mockMvc.perform(MockMvcRequestBuilders
                .get("/countries")
                .param("countryName", countryName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(countryId.toString())))
                .andExpect(jsonPath("$[0].countryName", is(countryName)));
    }

    @Test
    public void getCountryById() throws Exception {
        UUID countryId = UUID.randomUUID();
        String countryName = "England";
        when(countryService.getCountryById(countryId)).thenReturn(
                (Country.builder()
                        .id(countryId).countryName(countryName)
                        .build())
        );
        mockMvc.perform(MockMvcRequestBuilders
                .get("/countries/" + countryId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(countryId.toString())))
                .andExpect(jsonPath("$.countryName", is(countryName)));
    }

    @Test
    public void createCountry() throws Exception {
        String countryName = "England";
        UUID countryId = UUID.randomUUID();
        CountryCreateRequestDTO dto = CountryCreateRequestDTO.builder().countryName(countryName).build();
        when(countryService.createCountry(dto)).thenReturn(Country.builder().id(countryId).countryName(countryName).build());
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/countries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(countryId.toString())))
                .andExpect(jsonPath("$.countryName", is(countryName)));


    }

}
