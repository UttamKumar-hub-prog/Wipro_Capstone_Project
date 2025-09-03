import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { ToastService } from './toast.service';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const toast = inject(ToastService);
  return next(req).pipe({
    error: (err: HttpErrorResponse) => {
      const msg = err.error?.message || err.message || 'Request failed';
      toast.open(msg, 'DISMISS', 5000);
      throw err;
    }
  } as any);
};
