import { DatePipe } from '@angular/common';
import { TestBed } from '@angular/core/testing';
import { NumberGeneratorService } from './number-generator.service';

describe('NumberGeneratorService', () => {
  let service: NumberGeneratorService;
  let _datePipeSpy = jasmine.createSpyObj<DatePipe>(['transform']);
  let dateAsNumber = 20230101010101;
  let _datePipeTransformSpy = _datePipeSpy.transform.and.returnValue(String(dateAsNumber));

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        {
          provide: DatePipe,
          useValue: _datePipeSpy
        }
      ]
    });

    service = TestBed.inject(NumberGeneratorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('getDateAsNumber, should return number', () => {
    const now = new Date();

    service.getDateAsNumber(now);

    expect(_datePipeTransformSpy).toHaveBeenCalledWith(now, service.datePattern);
  });
});
