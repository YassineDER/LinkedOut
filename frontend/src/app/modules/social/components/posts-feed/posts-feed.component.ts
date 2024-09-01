import {Component} from '@angular/core';
import {PostsService} from "../../services/posts.service";
import {Post} from "../../../../models/social/post";
import {UtilsService} from "../../../../services/utils.service";
import {HttpClientError} from "../../../shared/utils/http-client.error";
import {AlertType} from "../../../shared/utils/alert-type";
import {UserService} from "../../../home/services/user.service";

@Component({
  selector: 'app-posts-feed',
  templateUrl: './posts-feed.component.html',
  styleUrl: './posts-feed.component.css'
})
export class PostsFeedComponent {
    posts: Post[] = [];
    current_page = 0;
    size = 10;
    isLoading = false;

    constructor(private postsService: PostsService, protected utils: UtilsService,
                protected users: UserService ) {
        this.loadPosts();
    }

    loadPosts() {
        this.isLoading = true;
        this.postsService.getPosts(this.current_page, this.size, 'created')
            .then((page) => {
                this.posts = this.posts.concat(page.content);
                this.current_page++;
                this.isLoading = false;
            })
            .catch((e: HttpClientError) => {
                this.utils.alert('Error loading posts', AlertType.ERROR);
                this.isLoading = false;
            });
    }

    onScroll() {
        if (!this.isLoading)
            this.loadPosts();
    }

}