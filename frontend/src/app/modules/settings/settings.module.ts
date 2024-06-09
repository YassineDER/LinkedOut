import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SettingsRoutingModule } from './settings-routing.module';
import { ProfilePageComponent } from './components/profile-page/profile-page.component';
import { SettingsComponent } from './components/settings/settings.component';


@NgModule({
  declarations: [
    ProfilePageComponent,
    SettingsComponent
  ],
  imports: [
    CommonModule,
    SettingsRoutingModule
  ]
})
export class SettingsModule { }
