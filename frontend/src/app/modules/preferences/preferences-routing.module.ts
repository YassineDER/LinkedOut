import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MyProfileComponent} from "../profile/components/my-profile/my-profile.component";
import {SettingsComponent} from "./components/settings/settings.component";

const routes: Routes = [
    {
        path: '', children: [
        {path: '',  component: SettingsComponent, data: {animation: 'SettingsPage'}},
    ]
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PreferencesRoutingModule { }
