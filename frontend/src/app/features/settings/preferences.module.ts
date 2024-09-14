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
import {AccountFormModalComponent} from "./components/account-preferences/account-infos-modal/account-form-modal.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MfaFormModalComponent} from "./components/security-preferences/mfa-modal/mfa-form-modal.component";
import {StepOneComponent} from "./components/security-preferences/mfa-modal/steps/step-one/step-one.component";
import {StepTwoComponent} from "./components/security-preferences/mfa-modal/steps/step-two/step-two.component";
import {StepThreeComponent} from "./components/security-preferences/mfa-modal/steps/step-three/step-three.component";


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
        StepOneComponent,
        StepTwoComponent,
        StepThreeComponent,
    ],
    providers: [SettingsService]
})
export class PreferencesModule {
}
