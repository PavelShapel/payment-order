import { TestBed } from '@angular/core/testing';
import { MatSnackBar } from '@angular/material/snack-bar';

import { NotificatorService } from './notificator.service';

describe('NotificatorService', () => {
  let service: NotificatorService;
  let _snackBarSpy = jasmine.createSpyObj<MatSnackBar>(['openFromComponent']);
  let _snackBarOpenFromComponentSpy = _snackBarSpy.openFromComponent;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        {
          provide: MatSnackBar,
          useValue: _snackBarSpy
        }
      ]
    });
    service = TestBed.inject(NotificatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('showSuccess, with valid parameter, should invoke _snackBar.openFromComponent', () => {
    const message = 'message';

    service.showSuccess(message);

    expect(_snackBarOpenFromComponentSpy).toHaveBeenCalled();
  });

  it('showError, with valid parameter, should invoke _snackBar.openFromComponent', () => {
    const message = 'message';

    service.showError(message);

    expect(_snackBarOpenFromComponentSpy).toHaveBeenCalled();
  });

  it('showInfo, with valid parameter, should invoke _snackBar.openFromComponent', () => {
    const message = 'message';

    service.showInfo(message);

    expect(_snackBarOpenFromComponentSpy).toHaveBeenCalled();
  });
});
