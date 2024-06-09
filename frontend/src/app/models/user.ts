/**
 * User model, macthes the one in the backend
 */
export interface User{
    user_id: number;
    email: string;
    password: string;
    username: string;
    image_url?: string;
    using_mfa: boolean;
}
