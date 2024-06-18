import {Component, Input} from '@angular/core';
import {User} from "../../../../models/user";

@Component({
  selector: 'app-overview-profile',
  templateUrl: './overview.component.html',
  styleUrl: './overview.component.css',
    standalone: true,
    imports: [],
})
export class OverviewComponent {
    @Input() user!: User;

}
