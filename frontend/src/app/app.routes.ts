import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AuthGuard} from "./guards/auth.guard";
import {HomeComponent} from "./components/home/home.component";
import {OffersComponent} from "./components/offers/offers.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";

const routes: Routes = [
    // Application routes
    {path: '', component: HomeComponent, data: {animation: 'HomePage'}, title: 'LinkedOut'},
    {path: 'offers', canActivate: [AuthGuard], component: OffersComponent, data: {animation: 'OffersPage'}, title: 'Offres - LinkedOut'},
    {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard], data: {animation: 'ProfilePage'}, title: 'Mon profil - LinkedOut'},
    // Lazy loaded modules

    // 404
    {path: '**', component: NotFoundComponent, data: {animation: 'NotFoundPage'}, title: 'Page non trouvée - LinkedOut'}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
