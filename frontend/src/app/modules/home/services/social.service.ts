import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../../models/user";

@Injectable()
export class SocialService {

    constructor(private http: HttpClient) {
    }

    isConnection(user: User, user2: User) {
        // TODO
        return false
    }

    triggerMsg(profile: User) {
        // TODO
    }

    connect(profile: User) {
        // TODO
    }

    getProfileStats(id: number) {
        // TODO
    }
}
