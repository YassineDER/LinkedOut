import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MessagingRoutingModule } from './messaging-routing.module';
import {ConversationListComponent} from "./components/conversation-list/conversation-list.component";
import { MessagingComponent } from './components/messaging/messaging.component';


@NgModule({
  declarations: [ConversationListComponent, MessagingComponent],
  imports: [
    CommonModule,
    MessagingRoutingModule
  ]
})
export class MessagingModule { }
