import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {SocialRoutingModule} from './social-routing.module';
import {FeedComponent} from "./components/feed/feed.component";


@NgModule({
    declarations: [FeedComponent],
    imports: [
        CommonModule,
        SocialRoutingModule
    ]
})
export class SocialModule {
}
