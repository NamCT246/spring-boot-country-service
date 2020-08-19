import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable()
export class CountryService {
  constructor(private httpClient: HttpClient) {}

  getCountryByName(name: String): Observable<any> {
    return this.httpClient.get<any>(
      'http://localhost:8080/api/countries/' + name
    );
  }
}
