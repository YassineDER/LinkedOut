import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProfileComponent} from "../profile/components/profile/profile.component";
import {SettingsComponent} from "./components/settings/settings.component";

const routes: Routes = [
    {
        path: '', children: [
        {path: '',  component: SettingsComponent},
    ]
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PreferencesRoutingModule { }
