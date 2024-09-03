import {Directive, ElementRef, Input, OnChanges, SimpleChanges} from '@angular/core';
import {UtilsService} from "../../../services/utils.service";

@Directive({
  selector: '[appElapsedDate]',
})
export class ElpasedDateFormatDirective implements OnChanges {
    @Input() appElapsedDate!: number[];

    constructor(private el: ElementRef, private utils: UtilsService) {}

    ngOnChanges(changes: SimpleChanges): void {
        if (changes['appElapsedDate'])
            this.el.nativeElement.textContent = this.utils.getElapsedTime(this.appElapsedDate);
    }

}
