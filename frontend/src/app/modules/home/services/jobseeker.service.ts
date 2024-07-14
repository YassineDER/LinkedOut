import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {map, Observable} from "rxjs";
import {Jobseeker} from "../../../models/jobseeker";

@Injectable()
export class JobseekerService {
    api = environment.hostUrl + '/api/jobseeker';

    constructor(private http: HttpClient) {
    }

    suggestJobseekers(): Observable<Jobseeker[]> {
        return this.http.get(this.api + '/suggested').pipe(
            map((res: any) => res.data as Jobseeker[])
        );
    }
}
