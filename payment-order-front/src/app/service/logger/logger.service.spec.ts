import { TestBed } from '@angular/core/testing';

import { LoggerService } from './logger.service';

describe('LoggerService', () => {
  let service: LoggerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoggerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('consoleLog, with valid parameter, should invoke console.log', () => {
    const consoleLogSpy = spyOn(console, 'log');
    const message = 'message';

    service.consoleLog(message);

    expect(consoleLogSpy).toHaveBeenCalledWith(message);
  });

  it('consoleError, with valid parameter, should invoke console.error', () => {
    const consoleErrorSpy = spyOn(console, 'error');
    const message = 'message';

    service.consoleError(message);

    expect(consoleErrorSpy).toHaveBeenCalledWith(message);
  });
});
