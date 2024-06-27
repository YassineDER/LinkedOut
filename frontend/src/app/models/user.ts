/**
 * User model, macthes the one in the backend
 */
export interface User{
    user_id: number;
    email: string;
    username: string;
    image_url: string | null;
    using_mfa: boolean;
}
