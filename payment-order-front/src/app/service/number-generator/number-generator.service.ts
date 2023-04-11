import { Injectable } from '@angular/core';
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class NumberGeneratorService {
  datePattern = 'yyyyMMddHHmmss';

  constructor(public _datePipe: DatePipe) { }

  getDateAsNumber(date?: Date): number {
    return date ? this.transformDateAsNumber(date) : this.transformDateAsNumber(new Date);
  }

  private transformDateAsNumber(date: Date): number {
    return Number(this._datePipe.transform(date, this.datePattern));
  }
}
