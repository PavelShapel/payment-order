import { TestBed } from '@angular/core/testing';
import { LoggerService } from '../../logger/logger.service';
import { NotificatorService } from '../../notificator/notificator.service';
import { ErrorParserService } from '../error-parser/error-parser.service';

import { ErrorHandlerService } from './error-handler.service';

describe('ErrorHandlerService', () => {
  let service: ErrorHandlerService;
  let notificatorServiceSpy = jasmine.createSpyObj<NotificatorService>(['showError']);
  let loggerServiceSpy = jasmine.createSpyObj<LoggerService>(['consoleError']);
  let errorParserServiceSpy = jasmine.createSpyObj<ErrorParserService>(['getMessage', 'getStack']);

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        {
          provide: NotificatorService,
          useValue: notificatorServiceSpy
        },
        {
          provide: LoggerService,
          useValue: loggerServiceSpy
        },
        {
          provide: ErrorParserService,
          useValue: errorParserServiceSpy
        }
      ]
    });
    service = TestBed.inject(ErrorHandlerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('handleError, with valid parameter, should invoke errorParserService.getMessage', () => {
    const error = new Error('error');
    errorParserServiceSpy.getMessage.calls.reset();

    service.handleError(error);

    expect(errorParserServiceSpy.getMessage).toHaveBeenCalledWith(error);
  });

  it('handleError, with valid parameter, should invoke errorParserService.getStack', () => {
    const error = new Error('error');
    errorParserServiceSpy.getStack.calls.reset();

    service.handleError(error);

    expect(errorParserServiceSpy.getStack).toHaveBeenCalledWith(error);
  });

  it('handleError, with valid parameter, should invoke loggerService.consoleError', () => {
    const stackTrace = 'stack trace';
    const error = new Error('error');
    errorParserServiceSpy.getStack.and.returnValue(stackTrace);
    loggerServiceSpy.consoleError.calls.reset();

    service.handleError(error);

    expect(loggerServiceSpy.consoleError).toHaveBeenCalledWith(stackTrace);
  });

  it('handleError, with valid parameter, should invoke notificatorService.showError', () => {
    const message = 'message';
    const error = new Error('error');
    errorParserServiceSpy.getMessage.and.returnValue(message);
    notificatorServiceSpy.showError.calls.reset();

    service.handleError(error);

    expect(notificatorServiceSpy.showError).toHaveBeenCalledWith(message);
  });
});
