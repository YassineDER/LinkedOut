import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home/home.component';
import {SharedModule} from "../shared/shared.module";
import {UserService} from "./services/user.service";
import {SocialService} from "./services/social.service";
import {JobseekerService} from "./services/jobseeker.service";


@NgModule({
  declarations: [
    HomeComponent
  ],
    imports: [
        CommonModule,
        HomeRoutingModule,
        SharedModule
    ],
    providers: [
        UserService,
        SocialService,
        JobseekerService
    ]
})
export class HomeModule { }
