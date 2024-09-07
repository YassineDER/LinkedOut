import {Component} from '@angular/core';

@Component({
    selector: 'app-step-one',
    standalone: true,
    template: `
        <div class="flex flex-row items-center">
            <i class="bi bi-shield-check text-4xl text-primary"></i>
            <p class="p-4">Pour activer la vérification en deux étapes, vous aurez besoin d'une application
                d'authentification
                sur votre téléphone. Une fois activée, vous devrez entrer un code de vérification à chaque connexion.
            </p>
        </div>
    `,
    imports: [],
})
export class StepOneComponent {

}
