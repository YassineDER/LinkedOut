import {Comment} from "./comment";

export interface Post {
    post_id: number;
    description: string;
    image_url: string;
    created: number[];
    likes: number;
    comments: Comment[];
}
