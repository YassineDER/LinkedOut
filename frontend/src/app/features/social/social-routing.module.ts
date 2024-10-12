import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FeedComponent} from "./components/feed/feed.component";
import {NetworkComponent} from "./components/network/network.component";

const routes: Routes = [
    {
        path: '', children: [
            {path: '', redirectTo: 'feed', pathMatch: 'full'},
            {path: 'feed', component: FeedComponent, title: 'Fil d\'actualité - LinkedOut'}
        ]
    },
    {
        path: 'network', component: NetworkComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SocialRoutingModule {
}
