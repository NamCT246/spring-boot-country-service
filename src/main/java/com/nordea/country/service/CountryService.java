package com.nordea.country.service;

import java.util.ArrayList;
import java.util.List;
import com.nordea.country.dto.CountriesListResponseDto;
import com.nordea.country.dto.CountriesRequestDto;
import com.nordea.country.dto.CountriesResponseDto;
import com.nordea.country.dto.CountryRequestDto;
import com.nordea.country.dto.CountryResponseDto;
import com.nordea.country.exceptions.CountryServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CountryService {

        @Value("${country.api.endpoint}")
        private String countryEndpoint;

        @Autowired
        private WebClient client;

        private final String allCountriesEndpoint = "/all/";
        private final String countryByNameEndpoint = "/name/";

        private Flux<CountriesRequestDto> getAllCountriesFromService() {
                Flux<CountriesRequestDto> response =
                                client.get().uri(countryEndpoint + allCountriesEndpoint)
                                                .accept(MediaType.APPLICATION_JSON).retrieve()
                                                .bodyToFlux(CountriesRequestDto.class);
                // // .onErrorMap(CountryServiceException::new);

                return response;
        }

        public Mono<CountriesListResponseDto> getAllCountries() {
                Mono<List<CountriesResponseDto>> result =
                                getAllCountriesFromService()
                                                .map(res -> CountriesResponseDto.builder()
                                                                .countryCode(res.getAlpha2Code())
                                                                .name(res.getName()).build())
                                                .collectList();

                return result.flatMap(res -> Mono.just(new CountriesListResponseDto(res)));
        }

        private Mono<CountryRequestDto> getCountryByNameFromService(String countryName) {
                Mono<CountryRequestDto> response = client.get()
                                .uri(countryEndpoint + countryByNameEndpoint + countryName)
                                .accept(MediaType.APPLICATION_JSON).retrieve()
                                .bodyToMono(CountryRequestDto.class);

                return response;
        }

        public Mono<CountryResponseDto> getCountryByName(String countryName) {
                return getCountryByNameFromService(countryName)
                                .flatMap(res -> Mono.from(Mono.just(CountryResponseDto.builder()
                                                .name(res.getName()).capital(res.getCapital())
                                                .countryCode(res.getAlpha2Code())
                                                .flagFileUrl(res.getFlag())
                                                .population(res.getPopulation()).build())));
        }

}
