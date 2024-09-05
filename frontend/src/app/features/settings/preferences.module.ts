import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PreferencesRoutingModule} from './preferences-routing.module';
import {PreferencesComponent} from './components/preferences/preferences.component';
import {AccountPreferencesComponent} from './components/account-preferences/account-preferences.component';
import {SecurityPreferencesComponent} from './components/security-preferences/security-preferences.component';
import {
    NotificationsPreferencesComponent
} from './components/notifications-preferences/notifications-preferences.component';
import {SettingsService} from "./services/settings.service";
import {SharedModule} from "../shared/shared.module";
import {AccountInfosComponent} from "../shared/components/modals/settings/account-infos/account-infos.component";


@NgModule({
    declarations: [
        PreferencesComponent,
        AccountPreferencesComponent,
        SecurityPreferencesComponent,
        NotificationsPreferencesComponent,
    ],
    imports: [
        CommonModule,
        SharedModule,
        PreferencesRoutingModule,
        AccountInfosComponent
    ],
    providers: [SettingsService]
})
export class PreferencesModule {
}
