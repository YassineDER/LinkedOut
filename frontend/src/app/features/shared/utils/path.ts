/**
 * List of all paths/routes in the application (api endpoints excluded)
 */
export enum Path {
    HOME = '/',
    // Auth
    HOME_LOGIN = "/auth",
    LOGIN = HOME_LOGIN + '/login',
    REGISTER = HOME_LOGIN + '/register',
    REGISTER_JOBSEEKER = HOME_LOGIN + '/register/jobseeker',
    REGISTER_COMPANY = HOME_LOGIN + '/register/company',
    RESET_PASSWORD = HOME_LOGIN + '/password/reset',
    REQUEST_PASSWORD_RESET = RESET_PASSWORD + '/request',
    VERIFY_EMAIL = HOME_LOGIN + '/email/verify',
    //Social
    SOCIAL = '/social',
    FEED = SOCIAL + '/feed',
    NETWORK = SOCIAL + '/network',
    NOTIFICATIONS = SOCIAL + '/notifications',
    // Messaging
    MESSAGING = '/messaging',
    // Jobs
    JOBS = '/jobs',
    //Preferences
    PREFERENCES = '/preferences',
    PREFERENCES_ACCOUNT = PREFERENCES + '/account',
    PREFERENCES_SECURITY = PREFERENCES + '/security',
    PREFERENCES_NOTIFICATIONS = PREFERENCES + '/notifications',
    //Profile
    PROFILE = '/out',
    PROFILE_POSTS = PROFILE + '/posts',
}
