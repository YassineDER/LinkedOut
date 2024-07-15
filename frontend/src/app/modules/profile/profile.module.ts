import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ProfileRoutingModule} from './profile-routing.module';
import {ProfileComponent} from "./components/profile/profile.component";
import {UsersSuggestionsComponent} from "./components/users-suggestions/users-suggestions.component";
import {OverviewComponent} from "./components/overview-profile/overview.component";

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
