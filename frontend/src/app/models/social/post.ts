import {Comment} from "./comment";
import {Profile} from "./profile";

export interface Post {
    post_id: number;
    description: string;
    profile: Profile;
    imageName: string;
    created: number[];
    likes: number;
    comments: Comment[];
}
