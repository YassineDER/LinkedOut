import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AuthGuard} from "../auth/guards/auth.guard";
import {FeedComponent} from "./components/feed/feed.component";

const routes: Routes = [
    {path: '', canActivate: [AuthGuard], component: FeedComponent, data: {animation: 'FeedPage'}, title: 'Fil d\'actualité - LinkedOut'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SocialRoutingModule { }
