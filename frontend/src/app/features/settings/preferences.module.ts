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
import {SettingsTemplatesComponent} from "../shared/components/settings-templates/settings-templates.component";


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
        PreferencesRoutingModule
    ],
    providers: [SettingsService, SettingsTemplatesComponent]
})
export class PreferencesModule {
}
