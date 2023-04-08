import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ErrorParserService {
  getMessage(error: Error): string {
    return `${error.message ? error.message : 'empty message'}`;
  }

  getStack(error: Error): string {
    return `${error.stack ? error.stack : 'empty stack'}`;
  }
}
