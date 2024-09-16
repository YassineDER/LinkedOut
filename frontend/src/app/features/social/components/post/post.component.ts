import {Component, Input} from '@angular/core';
import {UserService} from "../../../home/services/user.service";
import {Post} from "../../../../models/social/post";

@Component({
    selector: 'app-post',
    templateUrl: './post.component.html',
    styleUrls: ['./post.component.css']
})
export class PostComponent {
    @Input() postInstance!: Post;
    @Input() isDescriptionExpanded: any;
    postIsLiked: boolean = false;

    constructor(protected users: UserService) {
    }

    toggleDescription(postId: number, event: Event) {
        event.preventDefault();
        this.isDescriptionExpanded[postId] = !this.isDescriptionExpanded[postId];
    }

    reactPost(post_id: number) {

    }

    commentPost(post_id: number) {

    }
}
