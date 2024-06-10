import {Injectable} from '@angular/core';
import {User} from "../../../models/user";
import {environment} from "../../../../environments/environment";

@Injectable()
export class UserService {
    url = environment.hostUrl + '/api/user';

    constructor() {
    }

    getUserByUsername(username: string): User {
        return {} as User;
    }
}
