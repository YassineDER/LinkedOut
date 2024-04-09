import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButton, MatFabAnchor, MatIconButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {MatFormFieldModule, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatDivider} from "@angular/material/divider";
import {MatSnackBarModule} from "@angular/material/snack-bar";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
      MatFabAnchor,
      MatIcon,
      MatFormFieldModule,
      MatInput,
      MatLabel,
      MatIconButton,
      MatButton,
      MatDivider,
      MatSnackBarModule
  ],
    exports: [
        MatFabAnchor,
        MatIcon,
        MatFormFieldModule,
        MatInput,
        MatLabel,
        MatIconButton,
        MatButton,
        MatDivider,
        MatSnackBarModule
    ]
})
export class MaterialModule { }
