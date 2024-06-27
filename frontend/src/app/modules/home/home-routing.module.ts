import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
    {
        path: '', component: HomeComponent, children: [
            {path: '', redirectTo: 'social', pathMatch: 'full'},
            {
                path: 'preferences',
                loadChildren: () => import('../preferences/preferences.module').then(m => m.PreferencesModule)
            },
            {
                path: 'social',
                loadChildren: () => import('../social/social.module').then(m => m.SocialModule),
            },
            {
                path: 'out',
                loadChildren: () => import('../profile/profile.module').then(m => m.ProfileModule),
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
