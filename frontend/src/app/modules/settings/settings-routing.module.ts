import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProfilePageComponent} from "./components/profile-page/profile-page.component";
import {SettingsComponent} from "./components/settings/settings.component";

const routes: Routes = [
    {path: '', children: [
        {path: '', redirectTo: 'settings', pathMatch: 'full'},
        {path: 'settings', component: SettingsComponent, data: {animation: 'SettingsPage'}},
        {path: 'profile', component: ProfilePageComponent, data: {animation: 'ProfilePage'}}
    ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SettingsRoutingModule { }
