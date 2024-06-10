import { ResolveFn } from '@angular/router';
import {Observable} from "rxjs";
import {User} from "../../../../models/user";
import {inject} from "@angular/core";
import {AuthService} from "../../../../services/auth.service";
import {UserService} from "../../../home/services/user.service";

export const userResolver: ResolveFn<User> = (route, state):User => {
  const auth = inject(UserService);
  return auth.getUserByUsername(route.params['username']);
};
