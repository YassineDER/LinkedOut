import { Injectable } from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class SnackBarService {

  constructor(private snackbar: MatSnackBar) { }

    alert(message: string, action: string) {
        this.snackbar.open(message, action, {
            duration: 3000,
            horizontalPosition: 'start',
            verticalPosition: 'bottom'
        })
    }
}
