import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {AuthGuard} from "./modules/auth/guards/auth.guard";
import {PrivacyPolicyComponent} from "./components/privacy-policy/privacy-policy.component";
import {TermsAndConditionsComponent} from "./components/terms-and-conditions/terms-and-conditions.component";

const routes: Routes = [
    // Application routes
    {
        path: '', title: 'LinkedOut', canActivate: [AuthGuard],
        loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule)
    },
    // Privacy policy
    {path: 'privacy-policy', component: PrivacyPolicyComponent, title: 'Politique de confidentialité - LinkedOut'},
    // Terms of service
    {path: 'terms-and-conditions', component: TermsAndConditionsComponent, title: 'Conditions d\'utilisation - LinkedOut'},
    // 404
    {path: '**', component: NotFoundComponent, title: 'Page non trouvée - LinkedOut'},

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
