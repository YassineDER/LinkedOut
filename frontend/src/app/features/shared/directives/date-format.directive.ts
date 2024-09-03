import {Directive, ElementRef, Input, OnChanges, SimpleChanges} from '@angular/core';
import {UtilsService} from "../../../services/utils.service";

@Directive({
  selector: '[appDateFormat]',
})
export class DateFormatDirective implements OnChanges {
    @Input() appDateFormat!: number[];

    constructor(private el: ElementRef, private utils: UtilsService) {}

    ngOnChanges(changes: SimpleChanges): void {
        if (changes['appDateFormat'])
            this.el.nativeElement.textContent = this.utils.formatDate(this.appDateFormat);
    }


}
