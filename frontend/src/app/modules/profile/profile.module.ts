import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';

import {ProfileRoutingModule} from './profile-routing.module';
import {ProfileComponent} from "./components/profile/profile.component";
import {UsersSuggestionsComponent} from "./components/users-suggestions/users-suggestions.component";
import {OverviewComponent} from "./components/overview-profile/overview.component";
import { StatsProfileComponent } from './components/stats-profile/stats-profile.component';
import { AboutProfileComponent } from './components/about-profile/about-profile.component';
import {ProfileService} from "./services/profile.service";
import { ActivityProfileComponent } from './components/activity-profile/activity-profile.component';

@NgModule({
    declarations: [ProfileComponent, UsersSuggestionsComponent, OverviewComponent, StatsProfileComponent, AboutProfileComponent, ActivityProfileComponent],
    imports: [
        CommonModule,
        ProfileRoutingModule,
        NgOptimizedImage,
    ],
    providers: [ProfileService]
})
export class ProfileModule {
}
