import {Comment} from "./comment";

export interface Post {
    post_id: number;
    description: string;
    image_name: string;
    created: number[];
    likes: number;
    comments: Comment[];
}
