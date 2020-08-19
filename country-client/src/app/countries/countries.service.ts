import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable()
export class CountriesService {
  constructor(private httpClient: HttpClient) {}

  getAllCountries(): Observable<any> {
    return this.httpClient.get<any>('http://localhost:8080/api/countries/');
  }
}
