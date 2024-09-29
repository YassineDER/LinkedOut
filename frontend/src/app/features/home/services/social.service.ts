import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../../models/user";
import {environment} from "../../../../environments/environment";

/**
 * Responsible for managing social interactions between users
 */
@Injectable()
export class SocialService {
    url = environment.hostUrl + '/api/social';

    constructor(private http: HttpClient) {
    }

    isConnection(userToCheck: User) {
        const otherProfileId = userToCheck.profile.profile_id;
        return new Promise<boolean>((resolve, reject) => {
                this.http.get<boolean>(this.url + '/profiles/connected/check', {
                    params: {profile_id: otherProfileId}
                }).subscribe({
                        next: (res) => resolve(res),
                        error: (err) => reject(err)
                });
            }
        );
    }

    connect(profile: User) {
        const otherProfileId = profile.profile.profile_id;
        return new Promise((resolve, reject) => {
            this.http.post(this.url + '/connect', {profile_id: otherProfileId}).subscribe({
                next: (res) => resolve(res),
                error: (err) => reject(err)
            });
        });
    }

}
