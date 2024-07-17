import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProfileComponent} from "../profile/components/profile/profile.component";
import {PreferencesComponent} from "./components/preferences/preferences.component";

const routes: Routes = [
    {
        path: '', children: [
        {path: '',  component: PreferencesComponent},
    ]
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PreferencesRoutingModule { }
