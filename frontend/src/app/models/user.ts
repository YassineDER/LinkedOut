import {Profile} from "./social/profile";

/**
 * User model, macthes the one in the backend
 */
export interface User{
    user_id: number;
    email: string;
    username: string;
    imageName: string;
    using_mfa: boolean;
    authorities: any[];
    profile: Profile;
    createdDate: number[];
}
