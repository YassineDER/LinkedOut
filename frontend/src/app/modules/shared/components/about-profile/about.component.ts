import {Component, Input} from '@angular/core';
import { User } from '../../../../models/user';

@Component({
  selector: 'app-about-profile',
  templateUrl: './about.component.html',
  styleUrl: './about.component.css',
    standalone: true,
    imports: [],
})
export class AboutComponent {
    @Input() user!: User;



}
