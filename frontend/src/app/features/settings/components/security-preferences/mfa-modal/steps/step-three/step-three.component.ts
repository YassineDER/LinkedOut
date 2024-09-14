import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-step-three',
  standalone: true,
  imports: [],
  templateUrl: './step-three.component.html'
})
export class StepThreeComponent {
    @Input() QrImage!: string;


}
