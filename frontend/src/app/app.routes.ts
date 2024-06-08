import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {NotFoundComponent} from "./components/not-found/not-found.component";

const routes: Routes = [
    // Application routes
    {
        path: '', data: {animation: 'HomePage'}, title: 'LinkedOut',
        loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule)
    },
    // Lazy loaded modules
    {path: 'social', loadChildren: () => import('./modules/social/social.module').then(m => m.SocialModule)},

    // 404
    {path: '**', component: NotFoundComponent, data: {animation: 'NotFoundPage'}, title: 'Page non trouvée - LinkedOut'}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
