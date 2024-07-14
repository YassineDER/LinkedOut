import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ProfileRoutingModule} from './profile-routing.module';
import {ProfileComponent} from "./components/profile/profile.component";
import {UserService} from "../home/services/user.service";
import {UsersSuggestionsComponent} from "./components/about-profile/users-suggestions.component";
import {OverviewComponent} from "./components/overview-profile/overview.component";
import {SocialService} from "../home/services/social.service";

@NgModule({
    declarations: [ProfileComponent],
    imports: [
        CommonModule,
        ProfileRoutingModule,
        UsersSuggestionsComponent,
        OverviewComponent,
    ],
})
export class ProfileModule {
}
