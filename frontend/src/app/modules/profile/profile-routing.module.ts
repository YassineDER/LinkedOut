import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MyProfileComponent} from "./components/my-profile/my-profile.component";
import {userResolver} from "../auth/utils/resolvers/user.resolver";
import {UserProfileComponent} from "./components/user-profile/user-profile.component";

const routes: Routes = [
    {path: '', component: MyProfileComponent, data: {animation: 'MyProfilePage'}},
    {path: ':username', component: UserProfileComponent, resolve: {user: userResolver}, data: {animation: 'ProfilePage'}},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfileRoutingModule { }
