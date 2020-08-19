import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

// 3rd party libs
import { VirtualScrollerModule } from 'ngx-virtual-scroller';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { CountriesComponent } from './countries/countries.component';
import { CountryComponent } from './country/country.component';

@NgModule({
  declarations: [AppComponent, HeaderComponent, CountriesComponent, CountryComponent],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    VirtualScrollerModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
