import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './components/home-component/home.component';
import {SharedModule} from "../shared/shared.module";
import {UserService} from "./services/user.service";
import {SocialService} from "./services/social.service";
import {NavComponent} from "./components/nav/nav.component";
import {FooterComponent} from "./components/footer/footer.component";


@NgModule({
  declarations: [
    HomeComponent, NavComponent, FooterComponent
  ],
    imports: [
        CommonModule,
        HomeRoutingModule,
        SharedModule,
    ],
    providers: [
        UserService,
        SocialService,
    ]
})
export class HomeModule { }
