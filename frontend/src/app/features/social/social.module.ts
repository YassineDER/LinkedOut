import {forwardRef, NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';

import {SocialRoutingModule} from './social-routing.module';
import {FeedComponent} from "./components/feed/feed.component";
import {ProfileOverviewComponent} from './components/profile-overview/profile-overview.component';
import {PostFormComponent} from './components/post-form/post-form.component';
import {PostsFeedComponent} from './components/posts-list/posts-feed.component';
import {PostsService} from "./services/posts.service";
import {FormsModule, NG_VALUE_ACCESSOR, ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../shared/shared.module";
import {InfiniteScrollModule} from "ngx-infinite-scroll";
import {EmojiModule} from "@ctrl/ngx-emoji-mart/ngx-emoji";
import {PickerComponent} from "@ctrl/ngx-emoji-mart";


@NgModule({
    declarations: [FeedComponent, ProfileOverviewComponent, PostFormComponent, PostsFeedComponent],
    imports: [
        CommonModule,
        SocialRoutingModule,
        NgOptimizedImage,
        FormsModule,
        ReactiveFormsModule,
        SharedModule,
        InfiniteScrollModule,
        EmojiModule,
        PickerComponent
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
