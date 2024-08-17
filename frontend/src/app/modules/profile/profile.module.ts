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
import { ExperienceProfileComponent } from './components/experience-profile/experience-profile.component';
import { EducationProfileComponent } from './components/education-profile/education-profile.component';
import { SkillsProfileComponent } from './components/skills-profile/skills-profile.component';
import {DateFormatDirective} from "../shared/directives/date-format.directive";
import {SharedModule} from "../shared/shared.module";

@NgModule({
    declarations: [ProfileComponent, UsersSuggestionsComponent, OverviewComponent, StatsProfileComponent, AboutProfileComponent, ActivityProfileComponent, ExperienceProfileComponent, EducationProfileComponent, SkillsProfileComponent],
    imports: [
        CommonModule,
        ProfileRoutingModule,
        NgOptimizedImage,
        SharedModule,
    ],
    providers: [ProfileService]
})
export class ProfileModule {
}
