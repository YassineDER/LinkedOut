import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../../models/user";
import {Stats} from "../../../models/stats";

@Injectable()
export class SocialService {
    public profile_stats?: Stats;

    constructor(private http: HttpClient) {
        this.profile_stats = {
            connections: 0,
            profile_views: 0,
            search_appearances: 0
        }
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

    get stats() {
        return this.profile_stats;
    }
}
