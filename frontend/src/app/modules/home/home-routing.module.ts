import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {AuthGuard} from "../auth/guards/auth.guard";

const routes: Routes = [
    {
        path: '', component: HomeComponent, children: [
            {path: '', redirectTo: 'social', pathMatch: 'full'},
            {
                path: 'settings',
                loadChildren: () => import('../settings/settings.module').then(m => m.SettingsModule)
            },
            {
                path: 'social',
                loadChildren: () => import('../social/social.module').then(m => m.SocialModule),
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
