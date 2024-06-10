import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PreferencesRoutingModule } from './preferences-routing.module';
import { MyProfileComponent } from '../profile/components/my-profile/my-profile.component';
import { SettingsComponent } from './components/settings/settings.component';


@NgModule({
  declarations: [
    SettingsComponent
  ],
  imports: [
    CommonModule,
    PreferencesRoutingModule
  ]
})
export class PreferencesModule { }
