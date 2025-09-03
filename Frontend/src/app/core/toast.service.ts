import { Injectable, inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({ providedIn: 'root' })
export class ToastService {
  private snack = inject(MatSnackBar);
  open(msg: string, action='OK', duration=3000) {
    this.snack.open(msg, action, { duration });
  }
}
