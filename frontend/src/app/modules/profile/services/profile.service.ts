import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../../models/user";
import {Profile} from "../../../models/profile";

@Injectable()
export class ProfileService {

    constructor(private http: HttpClient) { }

    getProfileOf(user: User): Profile {
        return {
            about: "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
            banner_url: "",
            user_id: user.user_id,
        }
    }

}
