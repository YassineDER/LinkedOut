import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ProfileRoutingModule} from './profile-routing.module';
import {ProfileComponent} from "./components/profile/profile.component";
import {UserService} from "../home/services/user.service";
import {AboutComponent} from "../shared/components/about-profile/about.component";
import {OverviewComponent} from "../shared/components/overview-profile/overview.component";

@NgModule({
    declarations: [ProfileComponent],
    imports: [
        CommonModule,
        ProfileRoutingModule,
        AboutComponent,
        OverviewComponent,
    ],
})
export class ProfileModule {
}
