import { Component, Inject } from '@angular/core';
import { MatSnackBarRef, MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { NOTIFICATION } from 'src/app/model/notification';

@Component({
  selector: 'app-snack-bar',
  templateUrl: './snack-bar.component.html',
  styleUrls: ['./snack-bar.component.css']
})
export class SnackBarComponent {
  constructor(
    @Inject(MAT_SNACK_BAR_DATA) public message: string,
    public _snackBarRef: MatSnackBarRef<SnackBarComponent>
  ) { }

  getClass(): string {
    if (this.isSuccessNotification()) {
      return NOTIFICATION.success.class;
    } else if (this.isErrorNotification()) {
      return NOTIFICATION.error.class;
    } else if (this.isInfoNotification()) {
      return NOTIFICATION.info.class;
    } else {
      return NOTIFICATION.default.class;
    }
  }

  private isSuccessNotification(): boolean {
    return this.isMessageStartsWith(NOTIFICATION.success.value);
  }

  private isErrorNotification(): boolean {
    return this.isMessageStartsWith(NOTIFICATION.error.value);
  }

  private isInfoNotification(): boolean {
    return this.isMessageStartsWith(NOTIFICATION.info.value);
  }

  private isMessageStartsWith(notification: string): boolean {
    return this.message.startsWith(notification);
  }
}
