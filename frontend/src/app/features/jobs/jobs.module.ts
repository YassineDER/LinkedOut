import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { JobsRoutingModule } from './jobs-routing.module';
import { JobsComponent } from './components/jobs/jobs.component';


@NgModule({
  declarations: [
    JobsComponent
  ],
  imports: [
    CommonModule,
    JobsRoutingModule
  ]
})
export class JobsModule { }
