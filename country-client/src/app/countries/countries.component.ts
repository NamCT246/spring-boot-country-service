import { Component, OnInit } from '@angular/core';
import { throwError } from 'rxjs';

import { CountriesService } from './countries.service';
import { CountriesModel } from './countries-response';

@Component({
  selector: 'app-countries',
  templateUrl: './countries.component.html',
  styleUrls: ['./countries.component.css'],
  providers: [CountriesService],
})
export class CountriesComponent implements OnInit {
  countries: Array<CountriesModel>;

  constructor(private countriesService: CountriesService) {}

  ngOnInit(): void {}

  fetchCountries() {
    this.countriesService.getAllCountries().subscribe(
      (data) => {
        if (Array.isArray(data.countries) && data.countries.length > 0) {
          this.countries = data.countries;
        }
      },
      (error) => {
        throwError(error);
      }
    );
  }
}
