export interface AuthResponse {
    response: string;
    must_verify_mfa: boolean;
    qr_image: string;
}
