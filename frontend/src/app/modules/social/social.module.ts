import {forwardRef, NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';

import {SocialRoutingModule} from './social-routing.module';
import {FeedComponent} from "./components/feed/feed.component";
import {ProfileOverviewComponent} from './components/profile-overview/profile-overview.component';
import {PostFormComponent} from './components/post-form/post-form.component';
import {PostsFeedComponent} from './components/posts-feed/posts-feed.component';
import {PostsService} from "./services/posts.service";
import {FormsModule, NG_VALUE_ACCESSOR, ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../shared/shared.module";


@NgModule({
    declarations: [FeedComponent, ProfileOverviewComponent, PostFormComponent, PostsFeedComponent],
    imports: [
        CommonModule,
        SocialRoutingModule,
        NgOptimizedImage,
        FormsModule,
        ReactiveFormsModule,
        SharedModule
    ],
    providers: [
        PostsService,
        // {
        //     provide: NG_VALUE_ACCESSOR,
        //     useExisting: forwardRef(() =>),
        //     multi: true,
        // }
    ]
})
export class SocialModule {
}
