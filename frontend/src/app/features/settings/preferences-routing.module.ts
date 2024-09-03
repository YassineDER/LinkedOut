import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PreferencesComponent} from "./components/preferences/preferences.component";
import {AccountPreferencesComponent} from "./components/account-preferences/account-preferences.component";
import {SecurityPreferencesComponent} from "./components/security-preferences/security-preferences.component";
import {
    NotificationsPreferencesComponent
} from "./components/notifications-preferences/notifications-preferences.component";

const routes: Routes = [
    {
        path: '', component: PreferencesComponent, children: [
            {path: '', redirectTo: 'account', pathMatch: 'full'},
            {path: 'account', component: AccountPreferencesComponent},
            {path: 'security', component: SecurityPreferencesComponent},
            {path: 'notifications', component: NotificationsPreferencesComponent},
        ]
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PreferencesRoutingModule { }
