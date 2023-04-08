import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatSnackBarRef, MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { By } from '@angular/platform-browser';
import { NOTIFICATION } from 'src/app/model/notification';

import { SnackBarComponent } from './snack-bar.component';

describe('SnackBarComponent', () => {
  let component: SnackBarComponent;
  let fixture: ComponentFixture<SnackBarComponent>;
  let _snackBarRefSpy = jasmine.createSpyObj<MatSnackBarRef<SnackBarComponent>>(['dismissWithAction']);

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [
        SnackBarComponent
      ],
      providers: [
        {
          provide: MatSnackBarRef<SnackBarComponent>,
          useValue: _snackBarRefSpy
        },
        {
          provide: MAT_SNACK_BAR_DATA,
          useValue: ''
        },
      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(SnackBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('getClass, message startsWith SUCCESS, should return text-color-success', () => {
    component.message = NOTIFICATION.success.value + ': message';

    const result = component.getClass();

    expect(result).toBe(NOTIFICATION.success.class);
  });

  it('getClass, message startsWith ERROR, should return text-color-error', () => {
    component.message = NOTIFICATION.error.value + ': message';

    const result = component.getClass();

    expect(result).toBe(NOTIFICATION.error.class);
  });

  it('getClass, message startsWith INFO, should return text-color-info', () => {
    component.message = NOTIFICATION.info.value + ': message';

    const result = component.getClass();

    expect(result).toBe(NOTIFICATION.info.class);
  });

  it('getClass, message startsWith unsuitable string, should return text-color-default', () => {
    component.message = NOTIFICATION.default.value + ': message';

    const result = component.getClass();

    expect(result).toBe(NOTIFICATION.default.class);
  });

  it('render span matSnackBarLabel, message startsWith SUCCESS, should include class text-color-success', () => {
    component.message = NOTIFICATION.success.value + ': message';
    fixture.detectChanges();

    const debugElement = fixture.debugElement.query(By.css('span[matSnackBarLabel]'));

    expect(debugElement.classes[NOTIFICATION.success.class]).toBeTruthy();
  });

  it('render span matSnackBarLabel, message startsWith ERROR, should include class text-color-error', () => {
    component.message = NOTIFICATION.error.value + ': message';
    fixture.detectChanges();

    const debugElement = fixture.debugElement.query(By.css('span[matSnackBarLabel]'));

    expect(debugElement.classes[NOTIFICATION.error.class]).toBeTruthy();
  });

  it('render span matSnackBarLabel, message startsWith INFO, should include class text-color-info', () => {
    component.message = NOTIFICATION.info.value + ': message';
    fixture.detectChanges();

    const debugElement = fixture.debugElement.query(By.css('span[matSnackBarLabel]'));

    expect(debugElement.classes[NOTIFICATION.info.class]).toBeTruthy();
  });

  it('render span matSnackBarLabel, message startsWith unsuitable string, should include class text-color-default', () => {
    component.message = NOTIFICATION.default.value + ': message';
    fixture.detectChanges();

    const debugElement = fixture.debugElement.query(By.css('span[matSnackBarLabel]'));

    expect(debugElement.classes[NOTIFICATION.default.class]).toBeTruthy();
  });

  it('render span matSnackBarLabel, should include message as content', () => {
    component.message = NOTIFICATION.success.value + ': message';
    fixture.detectChanges();

    const debugElement = fixture.debugElement.query(By.css('span[matSnackBarLabel]'));

    expect(debugElement.nativeElement.textContent).toContain(component.message);
  });


  it('click on button, should invoke _snackBarRef.dismissWithAction()', () => {
    fixture.debugElement.query(By.css('button[matSnackBarAction]')).nativeElement.click();

    expect(_snackBarRefSpy.dismissWithAction).toHaveBeenCalled();
  });
});
