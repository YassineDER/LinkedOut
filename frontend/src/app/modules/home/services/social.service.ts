import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Jobseeker} from "../../../models/jobseeker";

@Injectable()
export class SocialService {

  constructor(private http: HttpClient) {

  }

    isConnection(user: Jobseeker, user2: Jobseeker) {
      return Math.random() > 0.5;
    }
}
