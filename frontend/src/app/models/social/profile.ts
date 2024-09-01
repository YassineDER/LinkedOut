import {Comment} from "./comment";
import {Skill} from "../job/skill";
import {User} from "../user";
import {Post} from "./post";

export interface Profile {
    profile_id: number;
    bio: string;
    banner_name: string;
    connections: number;
    profile_views: number;
    user: User;
    posts: Post[];
    comments: Comment[];
    experiences: any[];
    studies: any[];
    skills: Skill[];

}
