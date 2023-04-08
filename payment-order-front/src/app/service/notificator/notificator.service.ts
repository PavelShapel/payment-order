import { Injectable } from '@angular/core';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { SnackBarComponent } from 'src/app/component/snack-bar/snack-bar.component';
import { NOTIFICATION } from 'src/app/model/notification';

@Injectable({
  providedIn: 'root'
})
export class NotificatorService {
  constructor(private _snackBar: MatSnackBar) { }

  showSuccess(message: string): void {
    this.openSnackBar(`${NOTIFICATION.success.value}: ${message}`);
  }

  showError(message: string): void {
    this.openSnackBar(`${NOTIFICATION.error.value}: ${message}`);
  }

  showInfo(message: string): void {
    this.openSnackBar(`${NOTIFICATION.info.value}: ${message}`);
  }

  openSnackBar(message: string): void {
    const config: MatSnackBarConfig = {
      duration: 5000,
      data: message,
      verticalPosition: 'top',
      horizontalPosition: 'center'
    };
    this._snackBar.openFromComponent(SnackBarComponent, config);
  }
}
