import {forwardRef, NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';

import {SocialRoutingModule} from './social-routing.module';
import {FeedComponent} from "./components/feed/feed.component";
import {ProfileOverviewComponent} from './components/profile-overview/profile-overview.component';
import {PostFormComponent} from './components/post-form/post-form.component';
import {PostsFeedComponent} from './components/posts-list/posts-feed.component';
import {PostsService} from "./services/posts.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../shared/shared.module";
import {InfiniteScrollModule} from "ngx-infinite-scroll";
import {EmojiModule} from "@ctrl/ngx-emoji-mart/ngx-emoji";
import {PickerComponent} from "@ctrl/ngx-emoji-mart";
import { PostComponent } from './components/post/post.component';


@NgModule({
    declarations: [FeedComponent, ProfileOverviewComponent, PostFormComponent, PostsFeedComponent, PostComponent],
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
    ]
})
export class SocialModule {
}
