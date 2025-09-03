import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Account } from './models';

@Injectable({ providedIn: 'root' })
export class AccountApi {
  private readonly base = environment.apiBaseUrl;

  constructor(private readonly http: HttpClient) {}

  create(body: Partial<Account>) {
    return this.http.post<Account>(`${this.base}/accounts/saveDetails`, body);
  }

  update(id: number, body: Partial<Account>) {
    return this.http.put<Account>(`${this.base}/accounts/${id}`, body);
  }

  

  getById(id: number) {
    return this.http.get<Account>(`${this.base}/accounts/${id}`);
  }

  getAll() {
    return this.http.get<Account[]>(`${this.base}/accounts/getAllAccounts`);
  }

  getByAddress(address: string) {
    return this.http.get<Account[]>(
      `${this.base}/accounts/address/${encodeURIComponent(address)}`
    );
  }

  getCustomerInfo(id: number) {
    return this.http.get<any>(`${this.base}/accounts/customer/${id}`);
  }
}
