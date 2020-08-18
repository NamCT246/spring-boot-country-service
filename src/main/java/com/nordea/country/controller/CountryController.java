package com.nordea.country.controller;

import java.util.List;
import com.nordea.country.dto.CountriesListResponseDto;
import com.nordea.country.dto.CountriesResponseDto;
import com.nordea.country.dto.CountryResponseDto;
import com.nordea.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/")
    public Mono<CountriesListResponseDto> getCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{countryName}")
    public Mono<CountryResponseDto> getCountryByName(@PathVariable String countryName) {
        return countryService.getCountryByName(countryName);
    }
}
