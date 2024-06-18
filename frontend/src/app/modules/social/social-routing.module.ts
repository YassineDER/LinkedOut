import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FeedComponent} from "./components/feed/feed.component";

const routes: Routes = [
    {
        path: '', children: [
            {path: '', redirectTo: 'feed', pathMatch: 'full'},
            {path: 'feed', component: FeedComponent, title: 'Fil d\'actualité - LinkedOut'}
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SocialRoutingModule {
}
