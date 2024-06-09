import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {AuthGuard} from "../auth/guards/auth.guard";

const routes: Routes = [
    {
        path: '', component: HomeComponent, children: [
            {path: '', redirectTo: 'feed', pathMatch: 'full'},
            {
                path: 'profile',
                loadChildren: () => import('../profile/profile.module').then(m => m.ProfileModule)
            },
            {
                path: 'feed',
                loadChildren: () => import('../feed/feed.module').then(m => m.FeedModule),
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class HomeRoutingModule {
}
