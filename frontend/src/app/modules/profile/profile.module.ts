import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';

import {ProfileRoutingModule} from './profile-routing.module';
import {ProfileComponent} from "./components/profile/profile.component";
import {UsersSuggestionsComponent} from "./components/users-suggestions/users-suggestions.component";
import {OverviewComponent} from "./components/overview-profile/overview.component";
import { StatsProfileComponent } from './components/stats-profile/stats-profile.component';

@NgModule({
    declarations: [ProfileComponent, UsersSuggestionsComponent, OverviewComponent, StatsProfileComponent],
    imports: [
        CommonModule,
        ProfileRoutingModule,
        NgOptimizedImage,
    ],
})
export class ProfileModule {
}
