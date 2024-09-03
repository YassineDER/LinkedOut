import {Directive, ElementRef, Input, OnInit, Renderer2} from '@angular/core';

@Directive({
    selector: '[appSrc]'
})
export class ParFilepathDirective implements OnInit {
    PAR = localStorage.getItem('PAR') || '';
    @Input() appSrc!: string;

    constructor(private el: ElementRef, private renderer: Renderer2) {
    }

    ngOnInit() {
        const filepath = this.PAR + this.appSrc;
        this.renderer.setAttribute(this.el.nativeElement, 'src', filepath);
    }

}
