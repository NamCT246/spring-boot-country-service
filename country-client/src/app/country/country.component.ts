import { Component, OnInit, Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { CountryService } from './country.service';
import { CountryModel } from './country-response';

@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.css'],
  providers: [CountryService],
})
export class CountryComponent implements OnInit {
  country: CountryModel;

  constructor(private countryService: CountryService) {}

  ngOnInit(): void {}

  isObjectEmpty(obj) {
    console.log(obj);
    return obj && Object.keys(obj).length === 0;
  }

  getCountryByName(name: String) {
    this.countryService.getCountryByName(name).subscribe(
      (data) => {
        this.country = data;
        console.log(data);
      },
      (error) => {
        throwError(error);
      }
    );
  }
}
