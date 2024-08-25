import { CanActivateFn } from '@angular/router';

export const parGuard: CanActivateFn = (route, state) => {
  return true;
};
