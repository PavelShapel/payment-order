import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoggerService {
  consoleLog(message: string): void {
    console.log(message);
  }

  consoleError(message: string): void {
    console.error(message);
  }
}