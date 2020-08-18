package com.nordea.country.service;

import java.util.List;
import com.nordea.country.dto.CountriesListResponseDto;
import com.nordea.country.dto.CountriesResponseDto;
import com.nordea.country.dto.CountryResponseDto;
import com.nordea.country.service.integration.CountryServiceCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CountryService {

        @Autowired
        private CountryServiceCaller caller;

        public Mono<CountriesListResponseDto> getAllCountries() {
                Mono<List<CountriesResponseDto>> result = caller.getAllCountriesFromService()
                                .map(res -> CountriesResponseDto.builder()
                                                .countryCode(res.getAlpha2Code())
                                                .name(res.getName()).build())
                                .collectList();

                return result.flatMap(res -> Mono.just(new CountriesListResponseDto(res)));
        }

        public Mono<CountryResponseDto> getCountryByName(String countryName) {
                return caller.getCountryByNameFromService(countryName)
                                .flatMap(res -> Mono.justOrEmpty(CountryResponseDto.builder()
                                                .name(res.getName()).capital(res.getCapital())
                                                .countryCode(res.getAlpha2Code())
                                                .flagFileUrl(res.getFlag())
                                                .population(res.getPopulation()).build()));
        }

}
