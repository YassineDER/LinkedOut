import { animate, style, transition, trigger } from '@angular/animations';

export const fadeAnimation = trigger('fadeAnimation', [
  transition('* <=> *', [
    style({ opacity: 0 }),
    animate('400ms', style({ opacity: 1 }))
  ])
]);

export const fadeInUpAnimation = trigger('fadeInUpAnimation', [
    transition('* <=> *', [
        style({ opacity: 0, transform: 'translateY(25px)' }),
        animate('350ms ease-out', style({ opacity: 1, transform: 'translateY(0)' }))
    ])
]);
