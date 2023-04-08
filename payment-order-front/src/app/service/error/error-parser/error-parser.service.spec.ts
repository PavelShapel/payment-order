import { TestBed } from '@angular/core/testing';

import { ErrorParserService } from './error-parser.service';

describe('ErrorParserService', () => {
  let service: ErrorParserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ErrorParserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('getMessage, with valid parameter, should return error message', () => {
    const error = new Error('error');

    const result = service.getMessage(error);

    expect(result).toBe(error.message);
  });

  it('getMessage, with empty parameter, should return empty message', () => {
    const error = new Error('');

    const result = service.getMessage(error);

    expect(result).toBe('empty message');
  });

  it('getStack, with valid parameter, should return stack trace', () => {
    const error = new Error('error')
    error.stack = 'stack trace';

    const result = service.getStack(error);

    expect(result).toBe(error.stack);
  });

  it('getStack, with empty parameter, should return empty stack trace', () => {
    const error = new Error('error')
    error.stack = '';

    const result = service.getStack(error);

    expect(result).toBe('empty stack');
  });
});
