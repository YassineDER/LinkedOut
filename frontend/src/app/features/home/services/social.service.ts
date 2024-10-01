import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../../models/user";
import {environment} from "../../../../environments/environment";
import {Profile} from "../../../models/social/profile";

/**
 * Responsible for managing social interactions between users
 */
@Injectable()
export class SocialService {
    url = environment.hostUrl + '/api/social';

    constructor(private http: HttpClient) {
    }

    isConnection(profileToCheck: Profile) {
        const otherProfileId = profileToCheck.profileId;
        return new Promise<boolean>((resolve, reject) => {
                this.http.get<boolean>(this.url + '/profiles/connected/check', {
                    params: {profile_id: otherProfileId},
                    headers: {'Content-Type': 'application/json'}
                }).subscribe({
                        next: (res) => {
                            resolve(res)
                        },
                        error: (err) => reject(err)
                });
            }
        );
    }

    connect(profile: Profile) {
        const otherProfileId = profile.profileId;
        return new Promise((resolve, reject) => {
            this.http.post(this.url + '/connect', null , {
                params: {profile_id: otherProfileId}
            }).subscribe({
                next: (res) => resolve(res),
                error: (err) => reject(err)
            });
        });
    }

}
