import {Directive, ElementRef, Input, OnChanges, SimpleChanges} from '@angular/core';

@Directive({
  selector: '[appDateFormat]',
})
export class DateFormatDirective implements OnChanges {
    @Input() appDateFormat!: number[];

    constructor(private el: ElementRef) {}

    ngOnChanges(changes: SimpleChanges): void {
        if (changes['appDateFormat'])
            this.formatDate();
    }

    private formatDate(): void {
        if (this.appDateFormat && this.appDateFormat.length === 7) {
            const [year, month, day, hour, minute, second, nanosecond] = this.appDateFormat;
            const date = new Date(year, month - 1, day, hour, minute, second, nanosecond / 1000000);
            this.el.nativeElement.textContent = date.toLocaleString();
        }
    }
}
