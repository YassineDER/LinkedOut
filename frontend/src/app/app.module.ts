import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from '../components/app/app.component';
import { AppRoutingModule } from "../app.routes";
import {LoginComponent} from "../components/login/login.component";
import {MatButton, MatFabAnchor, MatIconButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {MAT_FORM_FIELD_DEFAULT_OPTIONS, MatFormFieldModule, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MaterialModule} from "./material.module";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        MaterialModule,
    ],
  bootstrap: [AppComponent],
  providers: [
      {provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: {appearance: 'outline'}}
  ]
})
export class AppModule {}
