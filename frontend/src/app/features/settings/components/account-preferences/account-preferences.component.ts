import { Component } from '@angular/core';
import {SettingsTemplatesComponent} from "../../../shared/components/settings-templates/settings-templates.component";

@Component({
  selector: 'app-account-settings',
  templateUrl: './account-preferences.component.html',
  styleUrl: './account-preferences.component.css'
})
export class AccountPreferencesComponent {

    constructor(private settingsTemplates: SettingsTemplatesComponent) {
    }

    openAppSettings(event: Event) {
        event.preventDefault();
        this.settingsTemplates.requestModal(this.settingsTemplates.homeSettings);
    }

    openUserInfo(event: Event) {
        event.preventDefault();
        this.settingsTemplates.requestModal(this.settingsTemplates.userInfo);
    }

}
