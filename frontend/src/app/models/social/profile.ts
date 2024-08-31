import {Post} from "./post";
import {Comment} from "./comment";
import {Skill} from "../job/skill";

export interface Profile {
    profile_id: number;
    bio: string;
    banner_name: string;
    connections: number;
    profile_views: number;
    posts: Post[];
    comments: Comment[];
    experiences: any[];
    studies: any[];
    skills: Skill[];

}
