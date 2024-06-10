import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfileRoutingModule } from './profile-routing.module';
import {MyProfileComponent} from "./components/my-profile/my-profile.component";
import { UserProfileComponent } from './components/user-profile/user-profile.component';


@NgModule({
  declarations: [MyProfileComponent, UserProfileComponent],
  imports: [
    CommonModule,
    ProfileRoutingModule
  ]
})
export class ProfileModule { }
