import { Directive, Input } from '@angular/core';
import { NG_VALIDATORS, Validator, AbstractControl, FormControl } from '@angular/forms';

/**
 * Validator for matching password fields. Should be used as a directive for the password confirmation field.
 *
 * @example
 * <input type="password" [formControl]="pwd" />
 * <input type="password" [formControl]="confirmPwd" [appMatchPassword]="pwd" />
 * <div *ngIf="confirmPwd.hasError('passwordMismatch')">Passwords do not match</div>
 */
@Directive({
  selector: '[appMatchPassword]',
  providers: [{provide: NG_VALIDATORS, useExisting: MatchPasswordDirective, multi: true}]
})
export class MatchPasswordDirective implements Validator {
  @Input('appMatchPassword') passwordToCompare?: FormControl;

  validate(control: AbstractControl): {[key: string]: any} | null {
    const confirmPasswordControl = control;
    if (this.passwordToCompare && confirmPasswordControl) {
      const password = this.passwordToCompare.value;
      const confirmPassword = confirmPasswordControl.value;
      if (password !== confirmPassword) {
        return { 'passwordMismatch': true };
      }
    }
    return null;
  }
}
