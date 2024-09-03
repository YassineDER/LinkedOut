/**
 * Needed to build the login object to send to the server
 */
export interface LoginCredentials {
    email: string;
    password: string;
    code?: string;
}
