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
import {AccountFormModalComponent} from "../shared/components/modals/settings/account-infos/account-form-modal.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MfaFormModalComponent} from "../shared/components/modals/settings/mfa-form-modal/mfa-form-modal.component";


@NgModule({
    declarations: [
        PreferencesComponent,
        AccountPreferencesComponent,
        SecurityPreferencesComponent,
        NotificationsPreferencesComponent,
        AccountFormModalComponent,
        MfaFormModalComponent
    ],
    imports: [
        CommonModule,
        SharedModule,
        PreferencesRoutingModule,
        FormsModule,
        ReactiveFormsModule,
    ],
    providers: [SettingsService]
})
export class PreferencesModule {
}
