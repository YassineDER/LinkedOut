import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./components/home-component/home.component";
import {PARGuard} from "./guards/par.guard";

const routes: Routes = [
    {
        path: '', component: HomeComponent, canActivate: [PARGuard], children: [
            {path: '', redirectTo: 'social', pathMatch: 'full'},
            {
                path: 'preferences',
                loadChildren: () => import('../settings/preferences.module').then(m => m.PreferencesModule)
            },
            {
                path: 'social',
                loadChildren: () => import('../social/social.module').then(m => m.SocialModule),
            },
            {
                path: 'out',
                loadChildren: () => import('../profile/profile.module').then(m => m.ProfileModule),
            },
            {
                path: 'messaging',
                loadChildren: () => import('../messaging/messaging.module').then(m => m.MessagingModule),
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
