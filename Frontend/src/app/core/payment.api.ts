import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { PaymentRequest } from './models';

@Injectable({ providedIn: 'root' })
export class PaymentApi {
  private base = environment.apiBaseUrl;
  constructor(private http: HttpClient) {}
  transfer(req: PaymentRequest) { return this.http.post(`${this.base}/payments/transfer`, req); }
}
