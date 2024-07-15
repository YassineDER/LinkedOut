import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";

@Injectable()
export class JobseekerService {
    api = environment.hostUrl + '/api/jobseeker';

    constructor(private http: HttpClient) {
    }


}
