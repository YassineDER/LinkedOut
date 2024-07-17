import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PreferencesRoutingModule } from './preferences-routing.module';
import { PreferencesComponent } from './components/preferences/preferences.component';


@NgModule({
  declarations: [
    PreferencesComponent,
  ],
  imports: [
    CommonModule,
    PreferencesRoutingModule
  ]
})
export class PreferencesModule { }
