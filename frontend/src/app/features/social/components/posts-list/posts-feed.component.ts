import {Component, OnInit} from '@angular/core';
import {PostsService} from "../../services/posts.service";
import {Post} from "../../../../models/social/post";
import {UtilsService} from "../../../../services/utils.service";
import {HttpClientError} from "../../../shared/utils/http-client.error";

@Component({
  selector: 'app-posts-list',
  templateUrl: './posts-feed.component.html',
  styleUrl: './posts-feed.component.css'
})
export class PostsFeedComponent implements OnInit {
    posts: Post[] = [];
    current_page = 0;
    size = 10;
    isInitialLoading = true;
    isLoadingMore = false;
    isDescriptionExpanded: { [key: number]: boolean } = {};

    constructor(private postsService: PostsService, private utils: UtilsService) {
    }

    ngOnInit() {
        setTimeout(() => {
            this.loadPosts();
        }, 1200);
    }

    loadPosts() {
        if (this.isLoadingMore)
            return;

        this.isLoadingMore = true;
        this.postsService.getPosts(this.current_page, this.size, 'created,desc')
            .then((page) => {
                this.posts = this.posts.concat(page.content);
                this.current_page++;
                this.isInitialLoading = false;
                this.isLoadingMore = false;
            })
            .catch((e: HttpClientError) => {
                this.utils.alert('Error loading posts');
                this.isInitialLoading = false;
                this.isLoadingMore = false;
            });
    }

    onScroll() {
        if (!this.isLoadingMore && !this.isInitialLoading)
            this.loadPosts();
    }

    onPostCreated() {
        this.posts = [];
        this.current_page = 0;
        this.isInitialLoading = true;
        this.loadPosts();
    }

}
