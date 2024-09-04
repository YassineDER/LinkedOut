import {Component, TemplateRef} from '@angular/core';
import {UtilsService} from "../../../../services/utils.service";

@Component({
    selector: 'app-modal',
    template: `
        <div *ngIf="content" class="modal modal-open" role="dialog" id="dynamicModal">
            <div class="modal-box">
                <ng-container *ngTemplateOutlet="content" />
                <div class="modal-action">
                    <a href="#" class="btn" (click)="closeModal()">Close</a>
                </div>
            </div>
        </div>
    `,
})
export class ModalComponent {
    content: TemplateRef<any> | null = null;

    constructor(private utils: UtilsService) {
        this.utils.modal$.subscribe(templ => this.content = templ);
    }

    closeModal() {
        this.utils.callModal(null);
    }
}
