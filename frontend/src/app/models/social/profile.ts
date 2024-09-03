import {Comment} from "./comment";
import {Skill} from "../job/skill";
import {User} from "../user";
import {Post} from "./post";
import {Experience} from "../job/experience";
import {Education} from "../job/education";

export interface Profile {
    profile_id: number;
    bio: string;
    banner_name: string;
    connections: number;
    profile_views: number;
    posts: Post[];
    comments: Comment[];
    experiences: Experience[];
    studies: Education[];
    skills: Skill[];
    user: User;
}
