import {Component, Injectable, TemplateRef, ViewChild} from '@angular/core';
import {UtilsService} from "../../../../services/utils.service";

@Component({
  selector: 'app-settings-templates',
  templateUrl: './settings-templates.component.html',
})
@Injectable()
export class SettingsTemplatesComponent {
    @ViewChild('userInfo') userInfo!: TemplateRef<any>;
    @ViewChild('homeSettings') homeSettings!: TemplateRef<any>;

    constructor(private utils: UtilsService) {
    }

    requestModal(template: TemplateRef<any>) {
        this.utils.callModal(template);
    }
}
