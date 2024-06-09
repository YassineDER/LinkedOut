import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {SocialRoutingModule} from './social-routing.module';
import {FeedComponent} from "./components/feed/feed.component";
import {CoreModule} from "../core/core.module";
import {AuthModule} from "../auth/auth.module";


@NgModule({
    declarations: [FeedComponent],
    imports: [
        CommonModule,
        SocialRoutingModule
    ]
})
export class FeedModule {
}
