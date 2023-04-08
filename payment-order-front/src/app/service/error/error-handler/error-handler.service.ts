import { ErrorHandler, Injectable } from '@angular/core';
import { LoggerService } from '../../logger/logger.service';
import { NotificatorService } from '../../notificator/notificator.service';
import { ErrorParserService } from '../error-parser/error-parser.service';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService implements ErrorHandler {

  constructor(
    private notificatorService: NotificatorService,
    private loggerService: LoggerService,
    private errorParserService: ErrorParserService) { }

  handleError(error: Error): void {
    const message = this.errorParserService.getMessage(error);
    const stack = this.errorParserService.getStack(error);
    this.loggerService.consoleError(stack);
    this.notificatorService.showError(message);
  }
}
