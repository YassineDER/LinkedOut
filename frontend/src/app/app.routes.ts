import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {AuthGuard} from "./modules/auth/guards/auth.guard";

const routes: Routes = [
    // Application routes
    {
        path: '', title: 'LinkedOut', canActivate: [AuthGuard],
        loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule)
    },

    // 404
    {path: '**', component: NotFoundComponent, title: 'Page non trouvée - LinkedOut'}
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { useHash: true })],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
